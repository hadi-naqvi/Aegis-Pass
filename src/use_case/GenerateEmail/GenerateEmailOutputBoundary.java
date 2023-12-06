package use_case.GenerateEmail;

import use_case.Dashboard.DashboardOutputData;

public interface GenerateEmailOutputBoundary {

    /**
     * These methods are empty as Dashboard view simply displays accounts based on user(no failview)
     */
    void prepareSuccessView(GenerateEmailOutputData generateEmailOutputData);
}

