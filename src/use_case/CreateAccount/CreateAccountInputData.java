package use_case.CreateAccount;

import java.time.LocalDateTime;

public class CreateAccountInputData {

    final private String title;
    final private String username;
    final private String password;
    final private String secretKey;
    final private String url;
    final private String iconURL;
    final private String date;
    final private String notes;

    /**
     * Constructor method for the CreateAccount use case's input data
     * @param title The title of the account
     * @param username The username of the account
     * @param password The password of the account
     * @param secretKey The 2FA secret key of the account
     * @param url The URL of the account
     * @param iconURL The URL for the icon of the account
     * @param date The last modified date of the account
     * @param notes The notes for the account
     */
    public CreateAccountInputData(String title, String username, String password, String secretKey,
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
     * Getter method for the title of the account.
     *
     * @return The title of the account.
     */
    public String getTitle() {
        return this.title;
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
     * Getter method for the password of the account.
     *
     * @return The password of the account.
     */
    public String getPassword() {
        return this.password;
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
     * Getter method for the URL associated with the account.
     *
     * @return The URL of the account.
     */
    public String getURL() {
        return this.url;
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
     * Getter method for the date when the account information was last updated.
     *
     * @return The date of the last update.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Getter method for any additional notes or information related to the account.
     *
     * @return Additional notes or information about the account.
     */
    public String getNotes() {
        return this.notes;
    }
}
