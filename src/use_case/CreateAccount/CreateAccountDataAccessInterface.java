package use_case.CreateAccount;

import entity.AccountInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface CreateAccountDataAccessInterface {

    /**
     * Method gets all the currently signed in user's accounts
     * @return returns list of user's accounts
     */
    List<AccountInfo> getAccounts();

    void addAccount(String title, String username, String password, String secret, String url,
                    String iconURL, String date, String notes);
}
