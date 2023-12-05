package interface_adapter.Dashboard;

import use_case.Dashboard.DashboardInputBoundary;
import use_case.Dashboard.DashboardInputData;

public class DashboardController {
    public final DashboardInputBoundary dashboardInteractor;

    /**
     * Constructor method for the controller for the dashboard use case
     * @param dashboardInteractor The use case interactor object for the displayDash use case
     */
    public DashboardController(DashboardInputBoundary dashboardInteractor) {
        this.dashboardInteractor = dashboardInteractor;
    }

    /**
     * Method which is executed/triggered when the user enters the dashbaord view
     */
    public void execute() {
        DashboardInputData dashboardInputData = new DashboardInputData();
        dashboardInteractor.execute(dashboardInputData);
    }
}
