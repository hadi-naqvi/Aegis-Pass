package use_case.Authentication;

public interface AuthenticationOutputBoundary {
    /**
     * Method which updates the view manager model to display the Dashboard screen after authentication is complete
     * @param authenticationOutputData The SetupAuth use case output data
     */
    void prepareSuccessView(AuthenticationOutputData authenticationOutputData);

    /**
     * Method which updates the view manager model to display the failed Authentication screen
     * @param error The error message which explains why Authentication failed
     */
    void prepareFailView(String error);
}