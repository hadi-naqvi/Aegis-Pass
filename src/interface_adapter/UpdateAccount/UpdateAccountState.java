package interface_adapter.UpdateAccount;

import entity.AccountInfo;
import interface_adapter.CreateAccount.CreateAccountState;

import java.util.List;

public class UpdateAccountState {
    private List<AccountInfo> accounts;
    private int accountIndex;
    private String originalTitle = "";
    private String originalUser = "";
    private String title = "";
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String secretKey = "";
    private String url = "";
    private String iconURL = "";
    private String date;
    private String notes = "";


    /**
     * Constructor method for the UpdateAccount's view state
     * @param copy a copy of the Update Account state
     */
    public UpdateAccountState(UpdateAccountState copy) {
        this.accounts = copy.accounts;
        this.originalTitle = copy.originalTitle;
        this.originalUser = copy.originalUser;
        this.title = copy.title;
        this.username = copy.username;
        this.usernameError = copy.usernameError;
        this.password = copy.password;
        this.secretKey = copy.secretKey;
        this.url = copy.url;
        this.iconURL = copy.iconURL;
        this.date = copy.date;
        this.notes = copy.notes;
    }

    /**
     * Alternative constructor method for the UpdateAccount state for no copy
     * which keeps attributes initialized as null/empty strings
     */
    public UpdateAccountState() {
    }

    /**
     * Getter method for the user's accounts
     * @return The user's accounts
     */
    public List<AccountInfo> getAccounts() { return this.accounts; }


    /**
     * Setter method for the user's accounts
     * @param accounts the user's accounts
     */
    public void setAccounts(List<AccountInfo> accounts){this.accounts = accounts;}

    /**
     * Getter method for the accountIndex of the account.
     *
     * @return The accountIndex of the account.
     */
    public String getOriginalTitle() {
        return this.originalTitle;
    }

    /**
     * Setter method for the accountIndex of the account.
     *
     * @param index The new accountIndex for the account.
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle= originalTitle;
    }

    /**
     * Getter method for the accountIndex of the account.
     *
     * @return The accountIndex of the account.
     */
    public String getOriginalUser() {
        return this.originalUser;
    }

    /**
     * Setter method for the accountIndex of the account.
     *
     * @param index The new accountIndex for the account.
     */
    public void setOriginalUser(String originalUser) {
        this.originalUser= originalUser;
    }

    /**
     * Getter method for the title of the account.
     *
     * @return The title of the account.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter method for the title of the account.
     *
     * @param title The new title for the account.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method for the username associated with the account.
     *
     * @return The username of the account.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Setter method for the username associated with the account.
     *
     * @param username The new username for the account.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for the username error
     * @return The error message to be displayed for createAccount failure due to username
     */
    public String getUsernameError() {
        return this.usernameError;
    }

    /**
     * Setter method for the username error
     * @param error The error message to be set for a createAccount failure due to username
     */
    public void setUsernameError(String error) {
        this.usernameError = error;
    }

    /**
     * Getter method for the password of the account.
     *
     * @return The password of the account.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter method for the password of the account.
     *
     * @param password The new password for the account.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for the secret key associated with the account.
     *
     * @return The secret key of the account.
     */
    public String getSecretKey() {
        return this.secretKey;
    }

    /**
     * Setter method for the secret key associated with the account.
     *
     * @param secretKey The new secret key for the account.
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Getter method for the URL associated with the account.
     *
     * @return The URL of the account.
     */
    public String getURL() {
        return this.url;
    }

    /**
     * Setter method for the URL associated with the account.
     *
     * @param url The new URL for the account.
     */
    public void setURL(String url) {
        this.url = url;
    }

    /**
     * Getter method for the URL of the icon associated with the account.
     *
     * @return The URL of the icon.
     */
    public String getIconURL() {
        return this.iconURL;
    }

    /**
     * Setter method for the URL of the icon associated with the account.
     *
     * @param iconURL The new URL for the icon.
     */
    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    /**
     * Getter method for the date when the account information was last updated.
     *
     * @return The date of the last update.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Setter method for the date when the account information was last updated.
     *
     * @param date The new date of the last update.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter method for any additional notes or information related to the account.
     *
     * @return Additional notes or information about the account.
     */
    public String getNotes() {
        return this.notes;
    }

    /**
     * Setter method for any additional notes or information related to the account.
     *
     * @param notes The new notes for the account.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
