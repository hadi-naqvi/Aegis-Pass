package use_case.DeleteAccount;

public interface DeleteAccountOutputBoundary {

    /**
     * Method which updates the view manager model to display the Dashboard screen after Delete Account is complete
     * @param deleteAccountOutputData The SetupAuth use case output data
     */
    void prepareSuccessView(DeleteAccountOutputData deleteAccountOutputData);

    /**
     * Method which updates the view manager model to display the failed Delete Account screen
     * @param error The error message which explains why Delete Account failed
     */
    void prepareFailView(String error);

    void switchView();

}
