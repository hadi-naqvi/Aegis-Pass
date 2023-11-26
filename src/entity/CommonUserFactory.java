package entity;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {
    /**
     * Method which creates a new User object
     * @param username The user's username
     * @param password The user's password
     * @return A new user object/entity
     */
    @Override
    public User create(String username, String password) {
        return new CommonUser(username, password);
    }
}
