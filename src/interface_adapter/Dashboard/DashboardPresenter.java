package interface_adapter.Dashboard;


import interface_adapter.ViewManagerModel;
import use_case.Dashboard.DashboardOutputBoundary;
import use_case.Dashboard.DashboardOutputData;

public class DashboardPresenter implements DashboardOutputBoundary {
    private final DashboardViewModel dashboardViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor method for the Dashboard use case's presenter
     * @param dashboardViewModel The view model for the dashboard view
     */
    public DashboardPresenter(ViewManagerModel viewManagerModel,
                                   DashboardViewModel dashboardViewModel) {
        this.dashboardViewModel = dashboardViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Empty method(DashboardView simply displays accounts)
     * @param dashboardOutputData The dashboard Output Data
     */
    @Override
    public void prepareSuccessView(DashboardOutputData dashboardOutputData) {
        DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setAccounts(dashboardOutputData.getAccounts());
        dashboardViewModel.setState(dashboardState);
    }

    /**
     * Empty method(Dashboard View simply displays accounts)
     */
    @Override
    public void prepareFailView(String error) {
    }
}

