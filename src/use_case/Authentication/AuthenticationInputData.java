package use_case.Authentication;
public class AuthenticationInputData {
    final private String password;

    /**
     * Constructor method for the Authentication use case's input data
     * @param password The password being entered during authentication
     */
    public AuthenticationInputData(String password) {
        this.password = password;
    }

    /**
     * Getter method for the password being entered during authentication
     * @return The password being entered during authentication
     */
    public String getPassword() {
        return this.password;
    }
}