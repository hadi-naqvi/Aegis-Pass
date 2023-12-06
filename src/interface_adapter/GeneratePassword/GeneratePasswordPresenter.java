package interface_adapter.GeneratePassword;

import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.ViewManagerModel;
import use_case.GeneratePassword.GeneratePasswordOutputBoundary;
import use_case.GeneratePassword.GeneratePasswordOutputData;

public class GeneratePasswordPresenter implements GeneratePasswordOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;
    private final GeneratePasswordViewModel generatePasswordViewModel;
    public GeneratePasswordPresenter(ViewManagerModel viewManagerModel,
                                     DashboardViewModel dashboardViewModel,
                                     GeneratePasswordViewModel generatePasswordViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dashboardViewModel = dashboardViewModel;
        this.generatePasswordViewModel = generatePasswordViewModel;
    }
    /**
     * Method for the success view of the generate password use case
     *
     * @param generatePasswordOutputData The generate password use case's output data
     */
    @Override
    public void prepareSuccessView(GeneratePasswordOutputData generatePasswordOutputData) {
        GeneratePasswordState state = generatePasswordViewModel.getState();
        state.setPasswordField(generatePasswordOutputData.getPassword());
        generatePasswordViewModel.setState(state);
        generatePasswordViewModel.firePropertyChanged();
    }

    /**
     * Method for the fail view of the generate password use case
     *
     * @param error The error message of the generate password use case
     */
    @Override
    public void prepareFailView(String error) {
        GeneratePasswordState state = generatePasswordViewModel.getState();
        state.setGeneratePasswordError(error);
        generatePasswordViewModel.setState(state);
        generatePasswordViewModel.firePropertyChanged();
    }
}
