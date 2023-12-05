package use_case.Dashboard;

public interface DashboardOutputBoundary {
    /**
     * These methods are empty as Dashboard view simply displays accounts based on user(no failview)
     */
    void prepareSuccessView(DashboardOutputData dashboardOutputData);
    void prepareFailView(String error);
}
