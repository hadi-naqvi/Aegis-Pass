package interface_adapter.CheckBreach;

import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CheckBreach.CheckBreachOutputBoundary;
import use_case.CheckBreach.CheckBreachOutputData;

public class CheckBreachPresenter implements CheckBreachOutputBoundary {

    private final CheckBreachViewModel checkBreachViewModel;
    private final ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;

    /**
     * Constructor method for the Presenter of the Check Breach use case
     * @param viewManagerModel the View Manager Model for the application
     * @param checkBreachViewModel the View Model for the Check Breach use case
     * @param dashboardViewModel the View Model for the Dashboard
     */
    public CheckBreachPresenter(ViewManagerModel viewManagerModel, CheckBreachViewModel checkBreachViewModel,
                                DashboardViewModel dashboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.checkBreachViewModel = checkBreachViewModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    /**
     * Method which updates the view manager model to display the breach info for an email or password
     *
     * @param checkBreachOutputData the output data for the Check Breach use case (the breach info)
     */
    @Override
    public void prepareSuccessView(CheckBreachOutputData checkBreachOutputData) {
        CheckBreachState checkBreachState = checkBreachViewModel.getState();
        this.checkBreachViewModel.setState(checkBreachState);
        checkBreachState.setResults(checkBreachOutputData.getResults());
        checkBreachViewModel.firePropertyChanged();
    }

    /**
     * Method which updates the view manager model to display the failed message for the use case
     *
     * @param error the error message which explains why Check Breach failed
     */
    @Override
    public void prepareFailView(String error) {
        CheckBreachState checkBreachState = checkBreachViewModel.getState();
        checkBreachState.setError(error);
        checkBreachViewModel.firePropertyChanged();
    }

    /**
     * Method for switching to Dashboard view
     */
    @Override
    public void switchView() {
        DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setRightPanelView("dashboard");
        dashboardViewModel.setState(dashboardState);
        this.dashboardViewModel.firePropertyChanged();
    }

}
