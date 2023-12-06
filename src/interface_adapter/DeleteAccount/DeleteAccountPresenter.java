package interface_adapter.DeleteAccount;

import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.ViewManagerModel;
import use_case.DeleteAccount.DeleteAccountOutputBoundary;
import use_case.DeleteAccount.DeleteAccountOutputData;

public class DeleteAccountPresenter implements DeleteAccountOutputBoundary {

    private final DeleteAccountViewModel deleteAccountViewModel;
    private final DashboardViewModel dashboardViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor method for the Delete Account use case's presenter
     * @param viewManagerModel
     * @param deleteAccountViewModel
     * @param dashboardViewModel
     */
    public DeleteAccountPresenter(ViewManagerModel viewManagerModel, DeleteAccountViewModel deleteAccountViewModel,
                                  DashboardViewModel dashboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.deleteAccountViewModel = deleteAccountViewModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    /**
     * Method which updates the view manager model to display the Dashboard screen after Delete Account is complete
     * @param deleteAccountOutputData The SetupAuth use case output data
     */
    @Override
    public void prepareSuccessView(DeleteAccountOutputData deleteAccountOutputData) {
        DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setAccounts(deleteAccountOutputData.getAccounts());
        dashboardViewModel.setState(dashboardState);
        switchView();
    }

    /**
     * Method which updates the view manager model to display the failed Delete Account screen
     * @param error The error message which explains why Delete Account failed
     */
    @Override
    public void prepareFailView(String error) {
        // this shouldn't happen I think
    }

    /**
     * Switches the view to the dashboard
     */
    @Override
    public void switchView() {
        DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setRightPanelView("dashboard");
        dashboardViewModel.setState(dashboardState);
        this.dashboardViewModel.firePropertyChanged();
    }

}
