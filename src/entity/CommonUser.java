package entity;

public class CommonUser implements User {
    private final String username;
    private final String password;

    /**
     * Constructor method for the User entity
     * @param username The user's username
     * @param password The user's password
     */
    public CommonUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Getter method for the user's username
     * @return the user's username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Getter method for the user's password
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return this.password;
    }
}
