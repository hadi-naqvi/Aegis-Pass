package interface_adapter.Dashboard;


import entity.AccountInfo;

import java.util.List;

public class DashboardState {
    private List<AccountInfo> accounts;
    private String rightPanelView;


    /**
     * Constructor method for the Dashboard view's state
     * @param copy A copy of the Dashboard's state
     */
    public DashboardState(DashboardState copy) {
        this.accounts = copy.accounts;
        this.rightPanelView = copy.rightPanelView;
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
     * Getter method for the right panel's view
     * @return The right panel view
     */
    public String getRightPanelView() { return this.rightPanelView; }


    /**
     * Setter method for the right panel's view
     * @param rightPanelView the right panel view
     */
    public void setRightPanelView(String rightPanelView){this.rightPanelView = rightPanelView;}
}
