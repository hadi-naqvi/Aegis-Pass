package entity;

import java.time.LocalDateTime;
import java.util.Date;

public class CommonAccountInfo implements AccountInfo {
    private String title;
    private String username;
    private String password;
    private String secretKey;
    private String url;
    private String iconURL;
    private String date;
    private String notes;

    /**
     * Constructor method for an AccountInfo entity which stores account details/login info
     * @param title The title of the account
     * @param username The username of the account
     * @param password The password of the account
     * @param secretKey The 2FA secret key of the account
     * @param url The URL of the account
     * @param iconURL The URL for the icon of the account
     * @param date The last modified date of the account
     * @param notes The notes for the account
     */
    public CommonAccountInfo(String title, String username, String password, String secretKey,
                             String url, String iconURL, String date, String notes) {
        this.title = title;
        this.username = username;
        this.password = password;
        this.secretKey = secretKey;
        this.url = url;
        this.iconURL = iconURL;
        this.date = date;
        this.notes = notes;
    }

    /**
     * Constructor method for an AccountInfo entity which doesn't store any account details/login info
     */
    public CommonAccountInfo() {

    }

    /**
     * Getter method for the title of the account.
     *
     * @return The title of the account.
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter method for the title of the account.
     *
     * @param title The new title for the account.
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method for the username associated with the account.
     *
     * @return The username of the account.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Setter method for the username associated with the account.
     *
     * @param username The new username for the account.
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for the password of the account.
     *
     * @return The password of the account.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter method for the password of the account.
     *
     * @param password The new password for the account.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for the secret key associated with the account.
     *
     * @return The secret key of the account.
     */
    @Override
    public String getSecretKey() {
        return this.secretKey;
    }

    /**
     * Setter method for the secret key associated with the account.
     *
     * @param secretKey The new secret key for the account.
     */
    @Override
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Getter method for the URL associated with the account.
     *
     * @return The URL of the account.
     */
    @Override
    public String getURL() {
        return this.url;
    }

    /**
     * Setter method for the URL associated with the account.
     *
     * @param url The new URL for the account.
     */
    @Override
    public void setURL(String url) {
        this.url = url;
    }

    /**
     * Getter method for the URL of the icon associated with the account.
     *
     * @return The URL of the icon.
     */
    @Override
    public String getIconURL() {
        return this.iconURL;
    }

    /**
     * Setter method for the URL of the icon associated with the account.
     *
     * @param iconURL The new URL for the icon.
     */
    @Override
    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    /**
     * Getter method for the date when the account information was last updated.
     *
     * @return The date of the last update.
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Setter method for the date when the account information was last updated.
     *
     * @param date The new date of the last update.
     */
    @Override
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter method for any additional notes or information related to the account.
     *
     * @return Additional notes or information about the account.
     */
    @Override
    public String getNotes() {
        return this.notes;
    }

    /**
     * Setter method for any additional notes or information related to the account.
     *
     * @param notes The new notes for the account.
     */
    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
