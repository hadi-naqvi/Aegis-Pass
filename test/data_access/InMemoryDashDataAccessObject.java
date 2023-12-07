package data_access;

import entity.AccountInfo;
import entity.CommonAccountInfo;
import use_case.CreateAccount.CreateAccountDataAccessInterface;
import use_case.Dashboard.DashboardDataAccessInterface;
import use_case.DeleteAccount.DeleteAccountDataAccessInterface;
import use_case.LogOut.LogOutDataAccessInterface;
import use_case.UpdateAccount.UpdateAccountDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDashDataAccessObject implements DashboardDataAccessInterface, LogOutDataAccessInterface, CreateAccountDataAccessInterface, UpdateAccountDataAccessInterface, DeleteAccountDataAccessInterface {

    private int currentUserID = 1;
    private String encryptionKey = "$2a$15$sDwsCyD.ZiWm3zSh1lzR0e";

    // a map of an array of the title and username to a string array of the rest of the attributes
    private final Map<String[], String[]> accounts = new HashMap<>();

    @Override
    public void addAccount(String title, String username, String password, String secret, String url, String iconURL, String date, String notes) {
        accounts.put(new String[]{title, username}, new String[]{String.valueOf(accounts.size() + 1), String.valueOf(currentUserID), password, secret, url, iconURL, date, notes});
    }

    /**
     * Method gets all the currently signed in user's accounts
     *
     * @return returns list of user's accounts
     */
    @Override
    public List<AccountInfo> getAccounts() {
        List<AccountInfo> userAccounts = new ArrayList<>();
        for (String[] key : accounts.keySet()) {
            String[] currInfo = accounts.get(key);
            userAccounts.add(new CommonAccountInfo(key[0], key[1], currInfo[2], currInfo[3], currInfo[4], currInfo[5], currInfo[6], currInfo[7]));
        }
        return userAccounts;
    }

    /**
     * method that updates the information in the selected account
     *
     * @param originalTitle
     * @param originalUser
     * @param title
     * @param username
     * @param password
     * @param secret
     * @param url
     * @param iconURL
     * @param date
     * @param notes
     */
    @Override
    public void updateAccount(String originalTitle, String originalUser, String title, String username, String password, String secret, String url, String iconURL, String date, String notes) {
        this.accounts.remove(new String[]{originalTitle, originalUser});
        addAccount(title, username, password, secret, url, iconURL, date, notes);
    }

    /**
     * Method that deletes an account with a unique title and username combination
     *
     * @param title    the title of the account to be deleted
     * @param username the username of the account to be deleted
     */
    @Override
    public void deleteAccount(String title, String username) {
        accounts.remove(new String[]{title, username});
    }

    @Override
    public void setCurrentUserID(int userID) {
        this.currentUserID = userID;
    }

    @Override
    public int getCurrentUserID() {
        return this.currentUserID;
    }

    /**
     * Getter method for the encryption key
     *
     * @return The encryption key
     */
    @Override
    public String getEncryptionKey() {
        return this.encryptionKey;
    }

    /**
     * Setter method for the encryption key
     *
     * @param key
     */
    @Override
    public void setEncryptionKey(String key) {
        this.encryptionKey = key;
    }
}
