package use_case.SetupAuth;

import entity.CommonUser;
import entity.User;

public interface SetupAuthDataAccessInterface {
    /**
     * Method which saves the user's username and password for the password database
     * @param user The user which has their corresponding username and password
     */
    void save(User user);

    /**
     * Method which check's if the inputted username already exists
     * @param username The inputted username that needs to be checked
     */
    boolean existsByName(String username);
}
