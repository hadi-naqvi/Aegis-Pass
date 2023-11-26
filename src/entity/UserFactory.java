package entity;

public interface UserFactory {
    /**
     * Method which creates a new User object
     * @param username The user's username
     * @param password The user's password
     * @return A new user object/entity
     */
    User create(String username, String password);
}
