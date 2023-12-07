package use_case.GenerateEmail;

import java.io.IOException;

public interface GenerateEmailInputBoundary {
    /**
     * Method which contains the logic for generating an ephemeral email for the generate email use case
     * @param generateEmailInputData The input data for the use case interactor
     */
    void execute(GenerateEmailInputData generateEmailInputData) throws IOException, InterruptedException;

    /**
     * Method for switching to Dashboard view
     */
    void switchView();
}
