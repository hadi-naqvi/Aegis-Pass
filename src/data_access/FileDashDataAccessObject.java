package data_access;

import entity.AccountInfo;
import entity.AccountInfoFactory;
import entity.UserFactory;
import use_case.Dashboard.DashboardDataAccessInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDashDataAccessObject implements DashboardDataAccessInterface {
    private final AccountInfoFactory ACCOUNTFACTORY;
    private final Connection CONNECTION;
    private int currentUserID;
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
     * Method which retrieves all of the currently signed in user's accounts from the
     * database and saved them to the in-memory list of accounts
     */
    private void retrieveAccounts() {
        accounts = new ArrayList<AccountInfo>();
        String sql = "SELECT * FROM password_manager_data WHERE user_id = ?";
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setInt(1, this.currentUserID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    AccountInfo account = ACCOUNTFACTORY.create();
                    account.setTitle(resultSet.getString("title"));
                    account.setUsername(resultSet.getString("username"));
                    account.setPassword(resultSet.getString("password"));
                    account.setSecretKey(resultSet.getString("two_factor_secret_key"));
                    account.setURL(resultSet.getString("url"));
                    account.setIconURL(resultSet.getString("icon_url"));
                    account.setDate(resultSet.getDate("date"));
                    account.setNotes(resultSet.getString("notes"));

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
}
