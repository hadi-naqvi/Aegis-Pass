package use_case.LogOut;

public interface LogOutOutputBoundary {
    /**
     * Method which prepares the success view for the logout use case
     * @param logOutOutputData The output data for the use case
     */
    void prepareSuccessView(LogOutOutputData logOutOutputData);

    /**
     * Method which prepares the fail view for the logout use case
     * @param error The error for logout use case if it fails
     */
    void prepareFailView(String error);
}
