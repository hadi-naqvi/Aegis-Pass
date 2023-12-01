package interface_adapter.Dashboard;

import use_case.Dashboard.DashboardInputBoundary;
import use_case.Dashboard.DashboardInputData;

public class DashboardController {
    public final DashboardInputBoundary authenticationInteractor;

    /**
     * Constructor method for the controller for the authentication use case
     * @param dashboardInteractor The use case interactor object for the displayDash use case
     */
    public DashboardController(DashboardInputBoundary dashboardInteractor) {
        this.authenticationInteractor = dashboardInteractor;
    }

    /**
     * Method which is executed/triggered when the user enters the dashbaord view
     * @param userID The currently signed in user's ID
     */
    public void execute(int userID) {
        DashboardInputData dashboardInputData = new DashboardInputData(userID);
        authenticationInteractor.execute(dashboardInputData);
    }
}
