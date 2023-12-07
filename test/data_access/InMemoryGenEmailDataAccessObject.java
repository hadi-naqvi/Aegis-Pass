package data_access;

import use_case.GenerateEmail.GenerateEmailDataAccessInterface;

import java.io.IOException;

public class InMemoryGenEmailDataAccessObject implements GenerateEmailDataAccessInterface {
    /**
     * Method that generates ephemeral email using mail.gw API
     *
     * @param accountName the name of the account the user enters
     * @param passName    the password name
     * @return returns email which is created
     */
    @Override
    public String createEmail(String accountName, String passName) throws IOException, InterruptedException {
        return accountName + "@domain.com";
    }
}
