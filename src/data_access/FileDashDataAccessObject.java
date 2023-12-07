package data_access;

import entity.AccountInfo;
import entity.AccountInfoFactory;
import use_case.CreateAccount.CreateAccountDataAccessInterface;
import use_case.Dashboard.DashboardDataAccessInterface;
import use_case.DeleteAccount.DeleteAccountDataAccessInterface;
import use_case.LogOut.LogOutDataAccessInterface;
import use_case.UpdateAccount.UpdateAccountDataAccessInterface;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileDashDataAccessObject implements DashboardDataAccessInterface, LogOutDataAccessInterface, CreateAccountDataAccessInterface, UpdateAccountDataAccessInterface, DeleteAccountDataAccessInterface {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private final AccountInfoFactory ACCOUNTFACTORY;
    private final Connection CONNECTION;
    EncryptionStrategy encryptionStrategy = new AES256EncryptionStrategy();
    private int currentUserID;
    private String encryptionKey;
    private List<AccountInfo> accounts;

    /**
     * Constructor method for the Dashboard DAO
     * @param accountInfoFactory The factory object which will create new account entities
     * @param dbURL The database URL
     * @param dbUsername The username for database authentication
     * @param dbPassword The password for database authentication
     * @throws SQLException An exception thrown by SQL database connection
     */
    public FileDashDataAccessObject(AccountInfoFactory accountInfoFactory,
                                    String dbURL, String dbUsername, String dbPassword) throws SQLException {
        this.ACCOUNTFACTORY = accountInfoFactory;
        this.CONNECTION = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }

    /**
     * Method gets all the currently signed in user's accounts
     * @return returns list of user's accounts
     */
    @Override
    public List<AccountInfo> getAccounts() {
        this.retrieveAccounts();
        return this.accounts;
    }

    /**
     * Method that deletes an account with a unique title and username combination
     *
     * @param title    the title of the account to be deleted
     * @param username the username of the account to be deleted
     */
    @Override
    public void deleteAccount(String title, String username) {
        AccountInfo accountToDelete = getAccountFromTitleUsername(title, username);
        accounts.remove(accountToDelete);
        String sql = "DELETE FROM password_manager_data WHERE title = ? AND username = ?";
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, encryptionStrategy.encrypt(accountToDelete.getTitle(), this.encryptionKey));
            statement.setString(2, encryptionStrategy.encrypt(accountToDelete.getUsername(), this.encryptionKey));

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new RuntimeException("No rows were deleted.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method which adds an account to the list of accounts and stores in database
     * @param title the account's title
     * @param username the account's username
     * @param password the account's password
     * @param secretKey the account's secretKey
     * @param url the account's url
     * @param iconURL the account's iconURL
     * @param date the account's date
     * @param notes the account's notes
     */
    @Override
    public void addAccount(String title, String username, String password, String secretKey,
                           String url, String iconURL, String date, String notes) {
        AccountInfo account = ACCOUNTFACTORY.create(title, username, password, secretKey, url, iconURL, date, notes);
        accounts.add(account);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sql = "INSERT INTO password_manager_data (user_id, title, username, password, two_factor_secret_key, url, icon_url, date, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setInt(1, this.currentUserID);
            statement.setString(2, encryptionStrategy.encrypt(account.getTitle(), this.encryptionKey));
            statement.setString(3, encryptionStrategy.encrypt(account.getUsername(), this.encryptionKey));
            statement.setString(4, encryptionStrategy.encrypt(account.getPassword(), this.encryptionKey));
            statement.setString(5, encryptionStrategy.encrypt(account.getSecretKey(), this.encryptionKey));
            statement.setString(6, encryptionStrategy.encrypt(account.getURL(), this.encryptionKey));
            statement.setString(7, encryptionStrategy.encrypt(account.getIconURL(), this.encryptionKey));
            statement.setString(8, encryptionStrategy.encrypt(account.getDate(), this.encryptionKey));
            statement.setString(9, encryptionStrategy.encrypt(account.getNotes(), this.encryptionKey));

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method to update the account's changes in the database
     * @param index
     * @param updatedTitle
     * @param updatedUsername
     * @param updatedPassword
     * @param updatedKey
     * @param updatedURL
     * @param updatedIconURL
     * @param updatedDate
     * @param updatedNotes
     */
    public void updateAccount(String originalTitle, String originalUser, String updatedTitle, String updatedUsername, String updatedPassword,
                              String updatedKey, String updatedURL, String updatedIconURL,
                              String updatedDate, String updatedNotes) {
        String sql = "UPDATE password_manager_data " +
                "SET title = ?, username = ?, password = ?, two_factor_secret_key = ?, " +
                "url = ?, icon_url = ?, date = ?, notes = ? " +
                "WHERE title = ? AND username = ?";

        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, encryptionStrategy.encrypt(updatedTitle, this.encryptionKey));
            statement.setString(2, encryptionStrategy.encrypt(updatedUsername, this.encryptionKey));
            statement.setString(3, encryptionStrategy.encrypt(updatedPassword, this.encryptionKey));
            statement.setString(4, encryptionStrategy.encrypt(updatedKey, this.encryptionKey));
            statement.setString(5, encryptionStrategy.encrypt(updatedURL, this.encryptionKey));
            statement.setString(6, encryptionStrategy.encrypt(updatedIconURL, this.encryptionKey));
            statement.setString(7, encryptionStrategy.encrypt(updatedDate, this.encryptionKey));
            statement.setString(8, encryptionStrategy.encrypt(updatedNotes, this.encryptionKey));
            statement.setString(9, encryptionStrategy.encrypt(originalTitle, this.encryptionKey));
            statement.setString(10, encryptionStrategy.encrypt(originalUser, this.encryptionKey));

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method which retrieves all of the currently signed in user's accounts from the
     * database and saved them to the in-memory list of accounts
     */
    private void retrieveAccounts() {
        accounts = new ArrayList<AccountInfo>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sql = "SELECT * FROM password_manager_data WHERE user_id = ?";
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setInt(1, this.currentUserID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    AccountInfo account = ACCOUNTFACTORY.create();
                    account.setTitle(encryptionStrategy.decrypt(resultSet.getString("title"), this.encryptionKey));
                    account.setUsername(encryptionStrategy.decrypt(resultSet.getString("username"), this.encryptionKey));
                    account.setPassword(encryptionStrategy.decrypt(resultSet.getString("password"), this.encryptionKey));
                    account.setSecretKey(encryptionStrategy.decrypt(resultSet.getString("two_factor_secret_key"), this.encryptionKey));
                    account.setURL(encryptionStrategy.decrypt(resultSet.getString("url"), this.encryptionKey));
                    account.setIconURL(encryptionStrategy.decrypt(resultSet.getString("icon_url"), this.encryptionKey));
                    account.setDate(encryptionStrategy.decrypt(resultSet.getString("date"), this.encryptionKey));
                    account.setNotes(encryptionStrategy.decrypt(resultSet.getString("notes"), this.encryptionKey));

                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the account with the given title and username combination
     * @param title of account to be retrieved
     * @param username of account to be retrieved
     * @return the account with matching title and username (throw RuntimeException if not found)
     */
    private AccountInfo getAccountFromTitleUsername(String title, String username) {
        for (AccountInfo account : accounts) {
            if (Objects.equals(account.getTitle(), title) && Objects.equals(account.getUsername(), username)) {
                return account;
            }
        }

        throw new RuntimeException("Account with title " + title + " and username " + username + " could not be found and deleted.");
    }

    /**
     * Getter method for the currently signed in user
     * @return The current user
     */
    public int getCurrentUserID() {
        return this.currentUserID;
    }

    /**
     * Setter method for the currently signed in user
     * @param userID The current user
     */
    public void setCurrentUserID(int userID) {
        this.currentUserID = userID;
    }

    /**
     * Getter method for the encryption key
     * @return The encryption key
     */
    public String getEncryptionKey() {
        return this.encryptionKey;
    }

    /**
     * Setter method for the encryption key
     * @param key The encryption key
     */
    public void setEncryptionKey(String key) {
        this.encryptionKey = key;
    }
}
