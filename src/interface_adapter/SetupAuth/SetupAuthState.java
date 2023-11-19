package interface_adapter.SetupAuth;

public class SetupAuthState {
    private String password = "";
    private String passwordError = null;
    private String repeatedPassword = "";

    /**
     * Constructor method for the SetupAuth view's state
     * @param copy A copy of the SetupAuth state
     */
    public SetupAuthState(SetupAuthState copy) {
        this.password = copy.password;
        this.passwordError = copy.passwordError;
        this.repeatedPassword = copy.repeatedPassword;
    }

    /**
     * Alternative constructor method for the SetupAuth state which keeps attributes initialized as null/empty strings
     */
    public SetupAuthState() {

    }

    /**
     * Getter method for the password
     * @return The password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Getter method for the password error
     * @return The error message to be displayed for why the auth setup failed
     */
    public String getPasswordError() {
        return this.passwordError;
    }

    /**
     * Getter method for the repeated/confirmed password
     * @return The repeated/confirmed password
     */
    public String getRepeatedPassword() {
        return this.repeatedPassword;
    }

    /**
     * Setter method for the password
     * @param password The password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter method for the password error
     * @param error The error message to be set for why the auth setup failed
     */
    public void setPasswordError(String error) {
        this.passwordError = error;
    }

    /**
     * Setter method for the repeated/confirmed password
     * @param password The repeated/confirmed password to be set
     */
    public void setRepeatedPassword(String password) {
        this.repeatedPassword = password;
    }

    /**
     * Method which converts the SetupAuth view's state into a string
     * @return The SetupAuth view's state as a string
     */
    @Override
    public String toString() {
        return "SetupAuthState{" +
                "password='" + this.password + '\'' +
                ", passwordError='" + this.passwordError + '\'' +
                ", repeatPassword='" + this.repeatedPassword + '\'' +
                '}';
    }
}
