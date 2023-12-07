package interface_adapter.ScanItem;

import interface_adapter.Authentication.AuthenticationState;
import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.ScanItem.ScanItemViewModel;
import interface_adapter.SetupAuth.SetupAuthState;
import interface_adapter.ViewManagerModel;
import use_case.Dashboard.DashboardOutputData;
import use_case.ScanItem.ScanItemOutputBoundary;
import use_case.ScanItem.ScanItemOutputData;

public class ScanItemPresenter implements ScanItemOutputBoundary {
    private final ScanItemViewModel scanItemViewModel;
    private final ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;

    /**
     * Constructor method for the Scan Item use case's presenter
     * @param scanItemViewModel The view model for the scan item view
     * @param viewManagerModel The view model
     */
    public ScanItemPresenter(ViewManagerModel viewManagerModel,
                              ScanItemViewModel scanItemViewModel,
                             DashboardViewModel dashboardViewModel) {
        this.scanItemViewModel = scanItemViewModel;
        this.viewManagerModel = viewManagerModel;
        this.dashboardViewModel = dashboardViewModel;
    }


    /**
     * Method which updates the view manager model to display the analysis for a file
     * @param scanItemOutputData
     */
    @Override
    public void prepareSuccessView(ScanItemOutputData scanItemOutputData) {
        ScanItemState scanItemState = scanItemViewModel.getState();
        scanItemState.setResults(scanItemOutputData.getResults());
        this.scanItemViewModel.setState(scanItemState);
        scanItemViewModel.firePropertyChanged();
    }


    /**
     * Method which updates the view manager model to display the failed analysis for a file
     * @param error The error message which explains why ScanItem failed
     */
    @Override
    public void prepareFailView(String error) {
        ScanItemState scanItemState = scanItemViewModel.getState();
        scanItemState.setError(error);
        scanItemViewModel.firePropertyChanged();
    }

    /**
     * Method for switching to Dashboard view
     */
    public void switchView(){
        DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setRightPanelView("dashboard");
        dashboardViewModel.setState(dashboardState);
        this.dashboardViewModel.firePropertyChanged();
    }
}
