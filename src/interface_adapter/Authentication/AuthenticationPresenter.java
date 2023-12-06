package interface_adapter.Authentication;

import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Authentication.AuthenticationOutputBoundary;
import use_case.Authentication.AuthenticationOutputData;

public class AuthenticationPresenter implements AuthenticationOutputBoundary {
    private final AuthenticationViewModel authenticationViewModel;
    private final DashboardViewModel dashboardViewModel;
    private final ViewManagerModel viewManagerModel;

    private final SetupAuthViewModel setupAuthViewModel;

    /**
     * Constructor method for the Authentication use case's presenter
     * @param authenticationViewModel The view model for the authentication view
     * @param dashboardViewModel The view model for the dashboard view
     */
    public AuthenticationPresenter(ViewManagerModel viewManagerModel,
                                   AuthenticationViewModel authenticationViewModel,
                                   DashboardViewModel dashboardViewModel,
                                   SetupAuthViewModel setupAuthViewModel) {
        this.authenticationViewModel = authenticationViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.viewManagerModel = viewManagerModel;
        this.setupAuthViewModel = setupAuthViewModel;
    }

    /**
     * Method which updates the view manager model to display the dashboard screen after setup is complete
     * @param authenticationOutputData The Authentication use case output data
     */
    @Override
    public void prepareSuccessView(AuthenticationOutputData authenticationOutputData) {
        DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setRightPanelView("dashboard");
        this.dashboardViewModel.setState(dashboardState);

        this.viewManagerModel.setActiveView(dashboardViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
        this.dashboardViewModel.firePropertyChanged();
    }

    /**
     * Method which updates the view manager model to display the failed Authentication screen
     * @param error The error message which explains why Authentication failed
     */
    @Override
    public void prepareFailView(String error) {
        AuthenticationState authenticationState = authenticationViewModel.getState();
        authenticationState.setPasswordError(error);
        authenticationViewModel.firePropertyChanged();
    }

    public void switchViews(){
        viewManagerModel.setActiveView(setupAuthViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}