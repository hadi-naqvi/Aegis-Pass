package data_access;

import entity.User;
import use_case.SetupAuth.SetupAuthDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAuthDataAccessObject implements SetupAuthDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    /**
     * Method which saves the user's username and password to our "fake database"
     *
     * @param user The user which has their corresponding username and password
     */
    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
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

}
