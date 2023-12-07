package data_access;

import use_case.CheckBreach.CheckBreachDataAccessInterface;
import use_case.LogOut.LogOutDataAccessInterface;

import java.io.IOException;
import java.net.URISyntaxException;

public class InMemoryBreachDataAccessObject implements CheckBreachDataAccessInterface, LogOutDataAccessInterface {

    private int currentUserID;
    private String encryptionKey;

    /**
     * Method that checks an email for breaches using HaveIBeenPWNED's API
     *
     * @param email the email to be checked for
     * @return the breaches that the email has been in
     */
    @Override
    public String checkEmail(String email) throws IOException, InterruptedException, URISyntaxException {
        if (email.equals("a")) {
            return "Your email was found in breaches from: Deezer, Canva";
        } else {
            return "We couldn't find your email in any breaches";
        }
    }

    /**
     * Method that checks password for breaches using HaveIBeenPWNED's API
     *
     * @param password the password to be checked for
     * @return the breaches that the password has been in
     */
    @Override
    public String checkPassword(String password) throws IOException, InterruptedException, URISyntaxException {
        if (password.equals("x")) {
            return "Your password has been breached x times";
        } else {
            return "Your password has been breached 0 times";
        }
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
