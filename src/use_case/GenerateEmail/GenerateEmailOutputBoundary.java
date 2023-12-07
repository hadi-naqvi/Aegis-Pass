package use_case.GenerateEmail;

import use_case.Dashboard.DashboardOutputData;

public interface GenerateEmailOutputBoundary {

    /**
     * These methods are empty as Dashboard view simply displays accounts based on user(no failview)
     */
    void prepareSuccessView(GenerateEmailOutputData generateEmailOutputData);

    /**
     * Method which updates the view manager model to display the failed analysis for a file
     * @param error The error message which explains why Authentication failed
     */
    void prepareFailView(String error);

    /**
     * Method for switching to Dashboard view
     */
    void switchView();

}

