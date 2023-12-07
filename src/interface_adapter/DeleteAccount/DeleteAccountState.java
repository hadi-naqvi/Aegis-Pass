package interface_adapter.DeleteAccount;

import entity.AccountInfo;

import java.util.List;

public class DeleteAccountState {

    private List<AccountInfo> accounts;
    private String title = "";
    private String username = "";

    /**
     * Constructor method for the Delete Account use case's view state
     * @param copy a copy of the Delete Account's state
     */
    public DeleteAccountState(DeleteAccountState copy) {
        this.accounts = copy.getAccounts();
        this.title = copy.getTitle();
        this.username = copy.getUsername();
    }

    /**
     * Alternative constructor method for no copy, which keeps null/default values for attributes
     */
    public DeleteAccountState() {

    }

    /**
     * Getter method for the user's accounts
     * @return the user's accounts
     */
    public List<AccountInfo> getAccounts() {
        return accounts;
    }

    /**
     * Setter method for the user's accounts
     * @param accounts the user's accounts
     */
    public void setAccounts(List<AccountInfo> accounts) {
        this.accounts = accounts;
    }

    /**
     * Getter method for the title of the account to delete
     * @return the title of the account to delete
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method for the title of the account to delete
     * @param title the title of the account to delete
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method for the username of the account to delete
     * @return the username of the account to delete
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for the username of the account to delete
     * @param username the username of the account to delete
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
