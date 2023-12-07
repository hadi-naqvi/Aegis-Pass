package interface_adapter.GenerateEmail;

import interface_adapter.Authentication.AuthenticationState;

public class GenerateEmailState {

    private String accountName = "";

    private String passName = "";

    private String error = null;

    /**
     * Constructor method for the Generate Email view's state
     * @param copy A copy of the Generate Email's state
     */
    public GenerateEmailState(GenerateEmailState copy) {
        this.accountName = copy.accountName;
        this.passName = copy.passName;
        this.error = copy.error;
    }

    /**
     * Alternative constructor method for the Generate Email state for no copy
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
     * Getter method for the passName
     * @return The password
     */
    public String getPassName() {
        return passName;
    }

    /**
     * Getter method for the error
     * @return The error
     */
    public String getError() { return error; }

    /**
     * Setter method for the error
     * @param error the error
     */
    public void setError(String error) { this.error = error; }

    /**
     * Setter method for the passName
     * @param passName the password
     */
    public void setPassName(String passName) {
        this.passName = passName;
    }

    /**
     * Setter method for the accountName
     * @param accountName the account name
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
