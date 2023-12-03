package interface_adapter.Dashboard;


import entity.AccountInfo;

import java.util.List;

public class DashboardState {
    private List<AccountInfo> accounts;


    /**
     * Constructor method for the Dashboard view's state
     * @param copy A copy of the Dashboard's state
     */
    public DashboardState(DashboardState copy) {
        this.accounts = copy.accounts;
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
}
