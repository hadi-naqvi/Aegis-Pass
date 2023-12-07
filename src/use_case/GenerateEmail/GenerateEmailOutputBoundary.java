package use_case.GenerateEmail;

import use_case.Dashboard.DashboardOutputData;

public interface GenerateEmailOutputBoundary {

    /**
     * Method which updates the view manager model to display the email that is generated
     */
    void prepareSuccessView(GenerateEmailOutputData generateEmailOutputData);

    /**
     * Method which updates the view manager model to display failure when generating email
     * @param error The error message which explains why Authentication failed
     */
    void prepareFailView(String error);

    /**
     * Method for switching to Dashboard view
     */
    void switchView();

}

