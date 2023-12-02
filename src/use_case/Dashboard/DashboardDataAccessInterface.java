package use_case.Dashboard;

import entity.AccountInfo;

import java.util.ArrayList;
import java.util.List;

public interface DashboardDataAccessInterface {
    /**
     * Method gets all the currently signed in user's accounts
     * @return returns list of user's accounts
     */
    List<AccountInfo> getAccounts();

    void setCurrentUserID(int userID);
    int getCurrentUserID();

    /**
     * Getter method for the encryption key
     * @return The encryption key
     */
    String getEncryptionKey();

    /**
     * Setter method for the encryption key
     * @param key
     */
    void setEncryptionKey(String key);
}
