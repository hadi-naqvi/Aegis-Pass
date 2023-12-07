package use_case.GenerateEmail;

import java.io.IOException;

public interface GenerateEmailDataAccessInterface {
    /**
     * Method that generates ephemeral email using mail.gw API
     * @param accountName the name of the account the user enters
     * @param passName the password name
     * @return returns email which is created
     */
    String createEmail(String accountName, String passName) throws IOException, InterruptedException;

}
