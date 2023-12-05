package app;

import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.CreateAccount.CreateAccountController;
import interface_adapter.CreateAccount.CreateAccountPresenter;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.Dashboard.DashboardController;
import interface_adapter.Dashboard.DashboardPresenter;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.GeneratePassword.GeneratePasswordController;
import interface_adapter.GeneratePassword.GeneratePasswordViewModel;
import interface_adapter.LogOut.LogOutController;
import interface_adapter.LogOut.LogOutPresenter;
import interface_adapter.ViewManagerModel;
import use_case.CreateAccount.CreateAccountDataAccessInterface;
import use_case.CreateAccount.CreateAccountInputBoundary;
import use_case.CreateAccount.CreateAccountInteractor;
import use_case.CreateAccount.CreateAccountOutputBoundary;
import use_case.Dashboard.DashboardDataAccessInterface;
import use_case.Dashboard.DashboardInputBoundary;
import use_case.Dashboard.DashboardInteractor;
import use_case.Dashboard.DashboardOutputBoundary;
import use_case.LogOut.LogOutDataAccessInterface;
import use_case.LogOut.LogOutInputBoundary;
import use_case.LogOut.LogOutInteractor;
import use_case.LogOut.LogOutOutputBoundary;
import view.DashboardView;

public class DashboardUseCaseFactory {
    /**
     * Factory method which creates and returns a new DashboardView
     * @param viewManagerModel The view manager model
     * @param dashboardViewModel The dashboard view model
     * @param userDataAccessObject The DAO for the dashboard
     * @return A new DashboardView instance/object
     */
    public static DashboardView create(ViewManagerModel viewManagerModel,
                                       AuthenticationViewModel authenticationViewModel,
                                       DashboardViewModel dashboardViewModel, CreateAccountViewModel createAccountViewModel,
                                       GeneratePasswordViewModel generatePasswordViewModel,
                                       DashboardDataAccessInterface userDataAccessObject) {
        DashboardController dashboardController = createDashboardUseCase(viewManagerModel, dashboardViewModel,
                userDataAccessObject);
        LogOutController logOutController = createLogOutUseCase(viewManagerModel, authenticationViewModel,
                (LogOutDataAccessInterface) userDataAccessObject);
        CreateAccountController createAccountController = createCreateAccountUseCase(viewManagerModel, createAccountViewModel,
                dashboardViewModel, (CreateAccountDataAccessInterface) userDataAccessObject);
        GeneratePasswordController generatePasswordController = GeneratePasswordUseCaseFactory.create(viewManagerModel, dashboardViewModel, generatePasswordViewModel);
        return new DashboardView(dashboardViewModel, dashboardController, logOutController, createAccountController,
                createAccountViewModel, generatePasswordController, generatePasswordViewModel);
    }

    /**
     * Method which creates and returns a new controller object for the DisplayDash use case
     * @param viewManagerModel The view manager model
     * @param dashboardViewModel The view model for the dashboard view
     * @param userDataAccessObject The data access object for displayDash
     * @return A new controller for the displayDash use case
     */
    private static DashboardController createDashboardUseCase(ViewManagerModel viewManagerModel,
                                                              DashboardViewModel dashboardViewModel,
                                                              DashboardDataAccessInterface userDataAccessObject) {
        DashboardOutputBoundary dashboardPresenter = new DashboardPresenter(viewManagerModel, dashboardViewModel);

        DashboardInputBoundary dashboardUseCaseInteractor = new DashboardInteractor(userDataAccessObject,
                dashboardPresenter);

        return new DashboardController(dashboardUseCaseInteractor);
    }

    /**
     * Method which creates and returns a new controller object for the LogOut use case
     * @param viewManagerModel The view manager model
     * @param authenticationViewModel The authentication view model
     * @param userDataAccessObject The data access object
     * @return A new controller for the LogOut use case
     */
    private static LogOutController createLogOutUseCase(ViewManagerModel viewManagerModel,
                                                        AuthenticationViewModel authenticationViewModel,
                                                        LogOutDataAccessInterface userDataAccessObject) {
        LogOutOutputBoundary logOutPresenter = new LogOutPresenter(viewManagerModel, authenticationViewModel);

        LogOutInputBoundary logOutUseCaseInteractor = new LogOutInteractor(userDataAccessObject, logOutPresenter);

        return new LogOutController(logOutUseCaseInteractor);
    }

    /**
     * Method which creates and returns a new controller object for the CreateAccount use case
     * @param viewManagerModel The view manager model
     * @param createAccountViewModel The CreateAccount view model
     * @param userDataAccessObject The data access object
     * @return A new controller for the CreateAccount use case
     */
    private static CreateAccountController createCreateAccountUseCase(ViewManagerModel viewManagerModel,
                                                                      CreateAccountViewModel createAccountViewModel, DashboardViewModel dashboardViewModel,
                                                                      CreateAccountDataAccessInterface userDataAccessObject) {
        CreateAccountOutputBoundary createAccountPresenter = new CreateAccountPresenter(viewManagerModel, createAccountViewModel, dashboardViewModel);

        CreateAccountInputBoundary createAccountUseCaseInteractor = new CreateAccountInteractor(userDataAccessObject, createAccountPresenter);

        return new CreateAccountController(createAccountUseCaseInteractor);
    }
}
