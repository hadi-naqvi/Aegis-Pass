package data_access;

import use_case.CheckBreach.CheckBreachDataAccessInterface;
import use_case.LogOut.LogOutDataAccessInterface;

import java.io.IOException;
import java.net.URISyntaxException;

public class FileBreachDataAccessObject implements CheckBreachDataAccessInterface, LogOutDataAccessInterface {

    private final String API_KEY;
    private int currentUserID;
    private String encryptionKey;

    /**
     * Constructor for the Data Access Object of the Check Breach use case
     * @param api_key the API key for the HaveIBeenPWNED API
     */
    public FileBreachDataAccessObject(String api_key) {
        this.API_KEY = api_key;
    }

    /**
     * Method that checks an email for breaches using HaveIBeenPWNED's API
     *
     * @param email the email to be checked for
     * @return the breaches that the email has been in
     */
    @Override
    public String checkEmail(String email) throws IOException, InterruptedException, URISyntaxException {
        String response = getEmailResponse(email);
        return getEmailBreachesFromResponse(response);
    }

    private String getEmailResponse(String email) {
        return null;
    }

    private String getEmailBreachesFromResponse(String response) {
        return "usernames test";
    }

    /**
     * Method that checks password for breaches using HaveIBeenPWNED's API
     *
     * @param password the password to be checked for
     * @return the breaches that the password has been in
     */
    @Override
    public String checkPassword(String password) throws IOException, InterruptedException, URISyntaxException {
        String response = getPasswordResponse(password);
        return getPasswordBreachesFromResponse(response);
    }

    private String getPasswordResponse(String password) {
        return null;
    }

    private String getPasswordBreachesFromResponse(String response) {
        return "passwords test";
    }

    /**
     * Setter method for the currently signed in user's id
     *
     * @param userID the user id
     */
    @Override
    public void setCurrentUserID(int userID) {
        this.currentUserID = userID;
    }

    /**
     * Setter method for the encryption key
     *
     * @param key the encryption key
     */
    @Override
    public void setEncryptionKey(String key) {
        this.encryptionKey = key;
    }
}
