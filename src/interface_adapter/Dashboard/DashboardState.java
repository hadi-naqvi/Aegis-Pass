package interface_adapter.Dashboard;


import entity.AccountInfo;

import java.util.List;

public class DashboardState {

    private int userID;
    private String userSalt;
    private List<AccountInfo> accounts;


    /**
     * Constructor method for the Dashboard view's state
     * @param copy A copy of the Dashboard's state
     */
    public DashboardState(DashboardState copy) {
        this.userID = copy.userID; this.accounts = copy.accounts;
    }

    /**
     * Alternative constructor method for the Dashboard state for no copy
     * which keeps attributes initialized as null/empty strings
     */
    public DashboardState() {
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
     * Getter method for the userID
     * @return The userID
     */
    public int getUserID() { return this.userID; }


    /**
     * Setter method for the userID
     * @param userID the user ID
     */
    public void setUserID(int userID){this.userID = userID;}

    /**
     * Getter method for the user's kdf salt
     * @return The user's kdf salt
     */
    public String getUserSalt() {
        return this.userSalt;
    }

    /**
     * Setter method for the user's kdf salt
     * @param userSalt The user's salt
     */
    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    @Override
    public String toString() {
        return "DashboardState{" +
                "userID='" + this.userID + '\'' + '\'' +
                '}';
    }
}
