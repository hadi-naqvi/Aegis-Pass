package use_case.GeneratePassword;

import use_case.Dashboard.DashboardOutputData;

public interface GeneratePasswordOutputBoundary {
    /**
     * Method for the success view of the generate password use case
     * @param generatePasswordOutputData The generate password use case's output data
     */
    void prepareSuccessView(GeneratePasswordOutputData generatePasswordOutputData);

    /**
     * Method for the fail view of the generate password use case
     * @param error The error message of the generate password use case
     */
    void prepareFailView(String error);
}
