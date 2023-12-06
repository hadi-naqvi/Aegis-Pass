package data_access;

import entity.AccountInfo;
import entity.AccountInfoFactory;
import use_case.CreateAccount.CreateAccountDataAccessInterface;
import use_case.Dashboard.DashboardDataAccessInterface;
import use_case.LogOut.LogOutDataAccessInterface;
import use_case.UpdateAccount.UpdateAccountDataAccessInterface;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class FileDashDataAccessObject implements DashboardDataAccessInterface, LogOutDataAccessInterface, CreateAccountDataAccessInterface
, UpdateAccountDataAccessInterface {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private final AccountInfoFactory ACCOUNTFACTORY;
    private final Connection CONNECTION;
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
            statement.setString(2, encrypt(account.getTitle(), this.encryptionKey));
            statement.setString(3, encrypt(account.getUsername(), this.encryptionKey));
            statement.setString(4, encrypt(account.getPassword(), this.encryptionKey));
            statement.setString(5, encrypt(account.getSecretKey(), this.encryptionKey));
            statement.setString(6, encrypt(account.getURL(), this.encryptionKey));
            statement.setString(7, encrypt(account.getIconURL(), this.encryptionKey));
            statement.setString(8, encrypt(account.getDate(), this.encryptionKey));
            statement.setString(9, encrypt(account.getNotes(), this.encryptionKey));

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
            statement.setString(1, encrypt(updatedTitle, this.encryptionKey));
            statement.setString(2, encrypt(updatedUsername, this.encryptionKey));
            statement.setString(3, encrypt(updatedPassword, this.encryptionKey));
            statement.setString(4, encrypt(updatedKey, this.encryptionKey));
            statement.setString(5, encrypt(updatedURL, this.encryptionKey));
            statement.setString(6, encrypt(updatedIconURL, this.encryptionKey));
            statement.setString(7, encrypt(updatedDate, this.encryptionKey));
            statement.setString(8, encrypt(updatedNotes, this.encryptionKey));
            statement.setString(9, encrypt(originalTitle, this.encryptionKey));
            statement.setString(10, encrypt(originalUser, this.encryptionKey));

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
                    account.setTitle(decrypt(resultSet.getString("title"), this.encryptionKey));
                    account.setUsername(decrypt(resultSet.getString("username"), this.encryptionKey));
                    account.setPassword(decrypt(resultSet.getString("password"), this.encryptionKey));
                    account.setSecretKey(decrypt(resultSet.getString("two_factor_secret_key"), this.encryptionKey));
                    account.setURL(decrypt(resultSet.getString("url"), this.encryptionKey));
                    account.setIconURL(decrypt(resultSet.getString("icon_url"), this.encryptionKey));
                    account.setDate(decrypt(resultSet.getString("date"), this.encryptionKey));
                    account.setNotes(decrypt(resultSet.getString("notes"), this.encryptionKey));

                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    /**
     * Method which encrypts data using AES-256 given an encryption key
     * @param plaintext The data being encrypted as a string of regular characters
     * @param key The encryption key as a hexadecimal string
     * @return The encrypted data in a hexadecimal string
     */
    private static String encrypt(String plaintext, String key) {
        if (plaintext.equals("")){
            return "";
        }
        try {
            Key secretKey = new SecretKeySpec(hexStringToByteArray(key), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
            return byteArrayToHexString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method which decrypts data using AES-256 given an encryption key
     * @param ciphertext The data being decrypted as a hexademical string
     * @param key The encryption key as a hexadeicmal string
     * @return The decrypted ciphertext as a string of regular characters
     */
    private static String decrypt(String ciphertext, String key) {
        if (ciphertext.equals("")){
            return "";
        }
        try {
            Key secretKey = new SecretKeySpec(hexStringToByteArray(key), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method which converts a hex string to a byte array
     * @param hexString The hex string
     * @return The byte array
     */
    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Method which converts a byte array to a hexadecimal string
     * @param byteArray The byte array
     * @return The hexadecimal string
     */
    private static String byteArrayToHexString(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
