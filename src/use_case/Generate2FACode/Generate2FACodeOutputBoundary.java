package use_case.Generate2FACode;

import use_case.CreateAccount.CreateAccountOutputData;

public interface Generate2FACodeOutputBoundary {
    /**
     * Method which updates the view manager model to display the 2FA key
     * @param generate2FACodeOutputData The generate2FACode use case output data
     */
    void prepareSuccessView(Generate2FACodeOutputData generate2FACodeOutputData);

    /**
     * This will be an empty method as there is no failview
     * @param error
     */
    void prepareFailView(String error);
}
