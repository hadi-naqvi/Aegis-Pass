package app;

import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.Dashboard.DashboardController;
import interface_adapter.Dashboard.DashboardPresenter;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.GeneratePassword.GeneratePasswordController;
import interface_adapter.GeneratePassword.GeneratePasswordPresenter;
import interface_adapter.GeneratePassword.GeneratePasswordViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Dashboard.DashboardInputBoundary;
import use_case.Dashboard.DashboardInteractor;
import use_case.Dashboard.DashboardOutputBoundary;
import use_case.GeneratePassword.GeneratePasswordInputBoundary;
import use_case.GeneratePassword.GeneratePasswordInteractor;
import use_case.GeneratePassword.GeneratePasswordOutputBoundary;

public class GeneratePasswordUseCaseFactory {
    /**
     * Factory method which creates and returns a controller object for the generate password use case
     * @param viewManagerModel The view manager model
     * @param dashboardViewModel The dashboard view model
     * @param generatePasswordViewModel The generate password view model
     * @return A new controller object for the generate password use case
     */
    public static GeneratePasswordController createGeneratePasswordUseCase(ViewManagerModel viewManagerModel, DashboardViewModel dashboardViewModel, GeneratePasswordViewModel generatePasswordViewModel) {
        GeneratePasswordOutputBoundary generatePasswordPresenter = new GeneratePasswordPresenter(viewManagerModel, dashboardViewModel, generatePasswordViewModel);
        GeneratePasswordInputBoundary generatePasswordInteractor = new GeneratePasswordInteractor(generatePasswordPresenter);
        return new GeneratePasswordController(generatePasswordInteractor);
    }
}
