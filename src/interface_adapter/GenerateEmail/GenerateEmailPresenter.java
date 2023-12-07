package interface_adapter.GenerateEmail;

import interface_adapter.Dashboard.DashboardState;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.ScanItem.ScanItemState;
import interface_adapter.ScanItem.ScanItemViewModel;
import interface_adapter.ViewManagerModel;
import use_case.GenerateEmail.GenerateEmailOutputBoundary;
import use_case.GenerateEmail.GenerateEmailOutputData;

public class GenerateEmailPresenter implements GenerateEmailOutputBoundary {
    private final GenerateEmailViewModel generateEmailViewModel;
    private final ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;

    /**
     * Constructor method for the Generate Email use case's presenter
     * @param generateEmailViewModel The view model for the generate email view
     * @param viewManagerModel The view model
     */
    public GenerateEmailPresenter(ViewManagerModel viewManagerModel,
                             GenerateEmailViewModel generateEmailViewModel,
                             DashboardViewModel dashboardViewModel) {
        this.generateEmailViewModel = generateEmailViewModel;
        this.viewManagerModel = viewManagerModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    /**
     * Method which updates the view manager model to display the email that is generated
     * @param generateEmailOutputData
     */
    @Override
    public void prepareSuccessView(GenerateEmailOutputData generateEmailOutputData) {
        GenerateEmailState generateEmailState = generateEmailViewModel.getState();
        this.generateEmailViewModel.setState(generateEmailState);

        generateEmailState.setAccountName(generateEmailOutputData.getResult());
        generateEmailViewModel.firePropertyChanged();
    }

    /**
     * Method which updates the view manager model to display failure when generating email
     * @param error The error message which explains why Authentication failed
     */
    @Override
    public void prepareFailView(String error) {
        GenerateEmailState generateEmailState = generateEmailViewModel.getState();
        generateEmailState.setError(error);
        generateEmailViewModel.firePropertyChanged();
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
