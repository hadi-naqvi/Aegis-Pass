package use_case.UpdateAccount;

import use_case.CreateAccount.CreateAccountOutputData;

public interface UpdateAccountOutputBoundary {
    /**
     * Method which updates the view manager model to display the Dashboard screen after UpdateAccount is complete
     * @param updateAccoutOutputData The updateAccount use case output data
     */
    void prepareSuccessView(UpdateAccoutOutputData updateAccoutOutputData);

    /**
     * Method which updates the view manager model to display the failed UpdateAccount screen
     * @param error The error message which explains why UpdateAccount failed
     */
    void prepareFailView(String error);

    void switchView();
}
