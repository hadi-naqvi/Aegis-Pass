package use_case.Authentication;

public interface AuthenticationDataAccessInterface {
    /**
     * Method validates the user's password with their corresponding username
     * @param username The user's inputted username
     * @param password The user's inputted password
     * @return returns whether password matches username
     */
    boolean validate(String username, String password);



}