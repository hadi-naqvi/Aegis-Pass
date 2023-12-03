package interface_adapter.Authentication;

public class AuthenticationState {

    private String username = "";

    private String usernameError = null;

    private String password = "";
    private String passwordError = null;

    /**
     * Constructor method for the Authentication view's state
     * @param copy A copy of the Authentication's state
     */
    public AuthenticationState(AuthenticationState copy) {
        this.username = copy.username;
        this.usernameError = copy.usernameError;
        this.password = copy.password;
        this.passwordError = copy.passwordError;
    }

    /**
     * Alternative constructor method for the Authentication state for no copy
     * which keeps attributes initialized as null/empty strings
     */
    public AuthenticationState() {
    }

    /**
     * Getter method for the username
     * @return The username
     */
    public String getUsername() { return this.username; }


    /**
     * Getter method for the password
     * @return The password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Getter method for the password error
     * @return The error message to be displayed for an Authentication failure due to password
     */
    public String getPasswordError() {
        return this.passwordError;
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
     * @param error The error message to be set for an Authentication failure due to password
     */
    public void setPasswordError(String error) {
        this.passwordError = error;
    }

    /**
     * Setter method for the username
     * @param username The username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method which converts the Authentication view's state into a string
     * @return The Authentication view's state as a string
     */
    @Override
    public String toString() {
        return "AuthenticationState{" +
                "password='" + this.password + '\'' +
                ", passwordError='" + this.passwordError + '\'' +
                '}';
    }

}
