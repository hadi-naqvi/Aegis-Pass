package entity;

import java.time.LocalDateTime;
import java.util.Date;

public class CommonAccountInfoFactory implements AccountInfoFactory {
    /**
     * Factory method which creates and returns a new AccountInfo object given all its data
     *
     * @param title     The title of the account
     * @param username  The username of the account
     * @param password  The password of the account
     * @param secretKey The 2FA secret key of the account
     * @param url       The URL of the account
     * @param iconURL   The URL for the icon of the account
     * @param date      The last modified date of the account
     * @param notes     The notes for the account
     * @return A new AccountInfo object that stores the account details
     */
    @Override
    public AccountInfo create(String title, String username, String password, String secretKey, String url,
                              String iconURL, LocalDateTime date, String notes) {
        return new CommonAccountInfo(title, username, password, secretKey, url, iconURL, date, notes);
    }

    /**
     * Factory method which creates and returns a new AccountInfo object without any given data
     * @return A new AccountInfo object that doesn't store any data
     */
    public AccountInfo create() {
        return new CommonAccountInfo();
    }
}
