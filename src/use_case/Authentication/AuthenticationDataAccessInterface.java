package use_case.Authentication;

public interface AuthenticationDataAccessInterface {
    /**
     * Method validates the user's password with their corresponding username
     * @param username The user's inputted username
     * @param password The user's inputted password
     * @return returns whether password matches username
     */
    boolean validate(String username, String password);

    /**
     * Method which returns a username's corresponding user ID in the database
     * @param username The username
     * @return The user's corresponding user ID
     */
    int getUserID(String username);

    /**
     * Method which returns a username's corresponding kdf salt in the database
     * @param username The username
     * @return The user's corresponding kdf salt
     */
    String getUserSalt(String username);
}