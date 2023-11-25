package use_case.SetupAuth;
public class SetupAuthInputData {
    final private String username;
    final private String password;
    final private String repeatPassword;

    /**
     * Constructor method for SetupAuthInputData
     * @param username The username from the "username" input field
     * @param password The password from the password input field
     * @param repeatPassword The confirmed/repeated password from the "verify password" input field
     */
    public SetupAuthInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    /**
     * Getter method for the username
     * @return The username from the input field
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Getter method for the password
     * @return The password from the input field
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Getter method for the confirmed/repeated password
     * @return The confirmed/repeated password from the input field
     */
    public String getRepeatPassword() {
        return this.repeatPassword;
    }
}
