package use_case.UpdateAccount;

public interface UpdateAccountOutputBoundary {
    /**
     * Method which updates the view manager model to display the Dashboard screen after UpdateAccount is complete
     * @param updateAccountOutputData The updateAccount use case output data
     */
    void prepareSuccessView(UpdateAccountOutputData updateAccountOutputData);

    /**
     * Method which updates the view manager model to display the failed UpdateAccount screen
     * @param error The error message which explains why UpdateAccount failed
     */
    void prepareFailView(String error);

    void switchView();
}
