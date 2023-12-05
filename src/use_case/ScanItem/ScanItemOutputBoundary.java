package use_case.ScanItem;

import use_case.Dashboard.DashboardOutputData;

public interface ScanItemOutputBoundary {
    /**
     * Method which updates the view manager model to display the analysis for a file
     */
    void prepareSuccessView(ScanItemOutputData scanItemOutputData);

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
