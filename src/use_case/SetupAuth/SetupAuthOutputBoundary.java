package use_case.SetupAuth;

public interface SetupAuthOutputBoundary {
    /**
     * Method which updates the view manager model to display the authentication screen after setup is complete
     * @param setupAuthOutputData The SetupAuth use case output data
     */
    void prepareSuccessView(SetupAuthOutputData setupAuthOutputData);

    /**
     * Method which updates the view manager model to display the failed SetupAuth screen
     * @param error The error message which explains why SetupAuth failed
     */
    void prepareFailView(String error);

    /**
     * Method which updates the view manager model to display Authentication view if user already has account
     */
    void switchViews();
}
