package entity;

import java.util.Date;

public interface AccountInfo {
    /**
     * Getter method for the title of the account.
     * @return The title of the account.
     */
    public String getTitle();

    /**
     * Setter method for the title of the account.
     * @param title The new title for the account.
     */
    public void setTitle(String title);

    /**
     * Getter method for the username associated with the account.
     * @return The username of the account.
     */
    public String getUsername();

    /**
     * Setter method for the username associated with the account.
     * @param username The new username for the account.
     */
    public void setUsername(String username);

    /**
     * Getter method for the password of the account.
     * @return The password of the account.
     */
    public String getPassword();

    /**
     * Setter method for the password of the account.
     * @param password The new password for the account.
     */
    public void setPassword(String password);

    /**
     * Getter method for the secret key associated with the account.
     * @return The secret key of the account.
     */
    public String getSecretKey();

    /**
     * Setter method for the secret key associated with the account.
     * @param secretKey The new secret key for the account.
     */
    public void setSecretKey(String secretKey);

    /**
     * Getter method for the URL associated with the account.
     * @return The URL of the account.
     */
    public String getURL();

    /**
     * Setter method for the URL associated with the account.
     * @param url The new URL for the account.
     */
    public void setURL(String url);

    /**
     * Getter method for the URL of the icon associated with the account.
     * @return The URL of the icon.
     */
    public String getIconURL();

    /**
     * Setter method for the URL of the icon associated with the account.
     * @param iconURL The new URL for the icon.
     */
    public void setIconURL(String iconURL);

    /**
     * Getter method for the date when the account information was last updated.
     * @return The date of the last update.
     */
    public Date getDate();

    /**
     * Setter method for the date when the account information was last updated.
     * @param date The new date of the last update.
     */
    public void setDate(Date date);

    /**
     * Getter method for any additional notes or information related to the account.
     * @return Additional notes or information about the account.
     */
    public String getNotes();

    /**
     * Setter method for any additional notes or information related to the account.
     * @param notes The new notes for the account.
     */
    public void setNotes(String notes);
}