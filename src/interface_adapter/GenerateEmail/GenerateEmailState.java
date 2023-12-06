package interface_adapter.GenerateEmail;

import interface_adapter.Authentication.AuthenticationState;

public class GenerateEmailState {

    private String accountName = "";

    /**
     * Constructor method for the Authentication view's state
     * @param copy A copy of the Authentication's state
     */
    public GenerateEmailState(GenerateEmailState copy) {
        this.accountName = copy.accountName;
    }

    /**
     * Alternative constructor method for the Authentication state for no copy
     * which keeps attributes initialized as null/empty strings
     */
    public GenerateEmailState() {
    }

    /**
     * Getter method for the accountName
     * @return The username
     */
    public String getAccountName() { return this.accountName; }

    /**
     * Setter method for the accountName
     * @param accountName the account name
     * @return The account name
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
