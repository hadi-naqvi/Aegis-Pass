package use_case.Dashboard;

public interface DashboardInputBoundary {
    /**
     * Method which contains the logic for the Authentication use case (interactor) which is triggered to complete use case
     * @param dashboardInputData The input data for the use case interactor
     */
    void execute(DashboardInputData dashboardInputData);
}
