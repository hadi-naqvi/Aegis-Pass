package use_case.Dashboard;

import entity.AccountInfo;

import java.util.ArrayList;

public interface DashboardDataAccessInterface {
    /**
     * Method retrieves all the users accounts
     * @param username The user's inputted username
     * @return returns list of user's accounts
     */
    ArrayList<AccountInfo> retrieveAccounts(String username);
}
