package interface_adapter.CreateAccount;

import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CreateAccount.CreateAccountOutputBoundary;
import use_case.CreateAccount.CreateAccountOutputData;

public class CreateAccountPresenter implements CreateAccountOutputBoundary {
    private final CreateAccountViewModel createAccountViewModel;
    private final DashboardViewModel dashboardViewModel;
    private final ViewManagerModel viewManagerModel;


    /**
     * Constructor method for the Authentication use case's presenter
     * @param createAccountViewModel The view model for the authentication view
     * @param dashboardViewModel The view model for the dashboard view
     */
    public CreateAccountPresenter(ViewManagerModel viewManagerModel,
                                   CreateAccountViewModel createAccountViewModel,
                                   DashboardViewModel dashboardViewModel) {
        this.createAccountViewModel = createAccountViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Method which updates the view manager model to display the dashboard screen after createAccount is complete
     * @param createAccountOutputData The CreateAccount use case output data
     */
    @Override
    public void prepareSuccessView(CreateAccountOutputData createAccountOutputData) {
        DashboardState dashboardState = dashboardViewModel.getState();
        dashboardState.setAccounts(createAccountOutputData.getAccounts());
        dashboardViewModel.setState(dashboardState);

        switchView();
    }

    /**
     * Method which updates the view manager model to display the failed createAccount screen
     * @param error The error message which explains why creating an account failed
     */
    @Override
    public void prepareFailView(String error) {
        CreateAccountState createAccountState = createAccountViewModel.getState();
        createAccountState.setUsernameError(error);
        this.createAccountViewModel.firePropertyChanged();
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
