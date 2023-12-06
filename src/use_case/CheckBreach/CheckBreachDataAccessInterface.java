package use_case.CheckBreach;

import java.io.IOException;
import java.net.URISyntaxException;

public interface CheckBreachDataAccessInterface {

    /**
     * Method that checks an email for breaches using HaveIBeenPWNED's API
     * @param email the email to be checked for
     * @return the breaches that the email has been in
     */
    public String checkEmail(String email) throws IOException, InterruptedException, URISyntaxException;

    /**
     * Method that checks password for breaches using HaveIBeenPWNED's API
     * @param password the password to be checked for
     * @return the breaches that the password has been in
     */
    public String checkPassword(String password) throws IOException, InterruptedException, URISyntaxException;

}
