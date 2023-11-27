package data_access;

import entity.User;
import entity.UserFactory;
import org.mindrot.jbcrypt.BCrypt;
import use_case.Authentication.AuthenticationDataAccessInterface;
import use_case.SetupAuth.SetupAuthDataAccessInterface;

import java.sql.*;

public class FileAuthDataAccessObject implements SetupAuthDataAccessInterface, AuthenticationDataAccessInterface {
    private final UserFactory USERFACTORY;
    private final Connection CONNECTION;
    private final String PEPPER;

    /**
     * Constructor for FileAuthDataAccessObject
     * @param userFactory The factory object which will create new user entities
     * @throws SQLException An exception thrown by SQL database connection
     */
    public FileAuthDataAccessObject(UserFactory userFactory, String dbURL, String dbUsername, String dbPassword, String pepper) throws SQLException {
        this.USERFACTORY = userFactory;
        this.CONNECTION = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        this.PEPPER = pepper;
    }

    /**
     * Method which saves the user's username and password for the password database
     * @param user The user which has their corresponding username and password
     */
    public void save(User user) {
        try {
            String query = "INSERT INTO users (username, hashed_password) VALUES (?, ?)";
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, BCrypt.hashpw(user.getPassword() + this.PEPPER, BCrypt.gensalt(15)));
            int rowsAffected = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which check's if the inputted username already exists
     * @param username The inputted username that needs to be checked
     */
    public boolean existsByName(String username) {
        try {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method which validates the user's password with their corresponding username
     * @param username The user's inputted username
     * @param password The user's inputted password
     * @return whether the password is correct
     */
    public boolean validate(String username, String password) {
        try {
            String query = "SELECT hashed_password FROM users WHERE username = ?";
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return BCrypt.checkpw(password + this.PEPPER, resultSet.getString("hashed_password"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}