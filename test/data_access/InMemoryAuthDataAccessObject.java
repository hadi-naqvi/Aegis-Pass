package data_access;

import entity.User;
import org.mindrot.jbcrypt.BCrypt;
import use_case.Authentication.AuthenticationDataAccessInterface;
import use_case.SetupAuth.SetupAuthDataAccessInterface;

import java.security.SecureRandom;
import java.util.*;

public class InMemoryAuthDataAccessObject implements SetupAuthDataAccessInterface, AuthenticationDataAccessInterface {

    // a map of the username to a list of the rest of the attribtues of a user
    private final Map<String, List<String>> users = new HashMap<>();
    private final String PEPPER = "$2a$15$sDwsCyD.ZiWm3zSh1lzR0e";
    private final String SALT = "$2a$15$sDwsCyD.ZiWm3zSh1lzR0e";

    /**
     * Method which saves the user's username and password to our "fake database"
     *
     * @param user The user which has their corresponding username and password
     */
    @Override
    public void save(User user) {
        String userId = String.valueOf(this.users.size() + 1);
        String encryptedPassword = BCrypt.hashpw(user.getPassword() + this.PEPPER, this.SALT);
        String[] userData = {userId, encryptedPassword, SALT};
        users.put(user.getUsername(), new ArrayList<>(Arrays.asList(userData)));
    }

    /**
     * Method which check's if the inputted username already exists
     *
     * @param username The inputted username that needs to be checked
     */
    @Override
    public boolean existsByName(String username) {
        return users.containsKey(username);
    }

    /**
     * Method validates the user's password with their corresponding username
     *
     * @param username The user's inputted username
     * @param password The user's inputted password
     * @return returns whether password matches username
     */
    @Override
    public boolean validate(String username, String password) {
        String encryptedPassword = BCrypt.hashpw(password + this.PEPPER, this.SALT);
        return encryptedPassword.equals(users.get(username).get(1));
    }

    /**
     * Method which returns a username's corresponding user ID in the database
     *
     * @param username The username
     * @return The user's corresponding user ID
     */
    @Override
    public int getUserID(String username) {
        return Integer.parseInt(users.get(username).get(0));
    }

    /**
     * Method which returns a username's corresponding kdf salt in the database
     *
     * @param username The username
     * @return The user's corresponding kdf salt
     */
    @Override
    public String getUserSalt(String username) {
        return users.get(username).get(2);
    }

}
