package interface_adapter.UpdateAccount;

import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.ViewManagerModel;
import use_case.UpdateAccount.UpdateAccountOutputBoundary;
import use_case.UpdateAccount.UpdateAccountOutputData;

public class UpdateAccountPresenter implements UpdateAccountOutputBoundary {
    private final UpdateAccountViewModel updateAccountViewModel;
    private final DashboardViewModel dashboardViewModel;
    private final ViewManagerModel viewManagerModel;


    /**
     * Constructor method for the UpdateAccount use case's presenter
     * @param updateAccountViewModel The view model for the UpdateAccount view
     * @param dashboardViewModel The view model for the dashboard view
     */
    public UpdateAccountPresenter(ViewManagerModel viewManagerModel,
                                  UpdateAccountViewModel updateAccountViewModel,
                                  DashboardViewModel dashboardViewModel) {
        this.updateAccountViewModel = updateAccountViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Method which updates the view manager model to display the dashboard screen after updateAccount is complete
     * @param updateAccountOutputData The UpdateAccount use case output data
     */
    @Override
    public void prepareSuccessView(UpdateAccountOutputData updateAccountOutputData) {
        DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setAccounts(updateAccountOutputData.getAccounts());
        dashboardViewModel.setState(dashboardState);

        switchView();
    }

    /**
     * Method which updates the view manager model to display the failed updateAccount screen
     * @param error The error message which explains why updating an account failed
     */
    @Override
    public void prepareFailView(String error) {
        UpdateAccountState updateAccountState = updateAccountViewModel.getState();
        updateAccountState.setUsernameError(error);
        this.updateAccountViewModel.firePropertyChanged();
    }

    public void switchView(){
        DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setRightPanelView("dashboard");
        dashboardViewModel.setState(dashboardState);
        this.dashboardViewModel.firePropertyChanged();
    }
}
