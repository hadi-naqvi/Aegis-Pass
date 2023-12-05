package use_case.CreateAccount;

public interface CreateAccountOutputBoundary {
    /**
     * Method which updates the view manager model to display the Dashboard screen after CreateAccount is complete
     * @param createAccountOutputData The SetupAuth use case output data
     */
    void prepareSuccessView(CreateAccountOutputData createAccountOutputData);

    /**
     * Method which updates the view manager model to display the failed CreateAccount screen
     * @param error The error message which explains why CreateAccount failed
     */
    void prepareFailView(String error);

    void switchView();
}
