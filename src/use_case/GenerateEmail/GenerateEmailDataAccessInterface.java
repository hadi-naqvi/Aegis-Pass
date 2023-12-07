package use_case.GenerateEmail;

import java.io.IOException;

public interface GenerateEmailDataAccessInterface {
    String createEmail(String accountName, String passName) throws IOException, InterruptedException;

}
