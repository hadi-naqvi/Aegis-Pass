package use_case.UpdateAccount;

import entity.AccountInfo;

import java.util.List;

public interface UpdateAccountDataAccessInterface {
    /**
     * Method gets all the currently signed in user's accounts
     * @return returns list of user's accounts
     */
    List<AccountInfo> getAccounts();

    /**
     * method that updates the information in the selected account
     * @param accountIndex
     * @param title
     * @param username
     * @param password
     * @param secret
     * @param url
     * @param iconURL
     * @param date
     * @param notes
     */
    void updateAccount(String originalTitle, String originalUser, String title, String username, String password, String secret, String url,
                    String iconURL, String date, String notes);
}
