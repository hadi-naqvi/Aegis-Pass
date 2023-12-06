package use_case.DeleteAccount;

import entity.AccountInfo;

import java.util.List;

public interface DeleteAccountDataAccessInterface {

    /**
     * Method gets all the currently signed in user's accounts
     * @return returns list of user's accounts
     */
    List<AccountInfo> getAccounts();

    /**
     * Method that deletes an account with a unique title and username combination
     * @param title the title of the account to be deleted
     * @param username the username of the account to be deleted
     */
    void deleteAccount(String title, String username);

}
