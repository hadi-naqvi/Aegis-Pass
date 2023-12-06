package app;

import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.Dashboard.DashboardController;
import interface_adapter.Dashboard.DashboardPresenter;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.DeleteAccount.DeleteAccountController;
import interface_adapter.DeleteAccount.DeleteAccountPresenter;
import interface_adapter.DeleteAccount.DeleteAccountViewModel;
import interface_adapter.GeneratePassword.GeneratePasswordController;
import interface_adapter.GeneratePassword.GeneratePasswordViewModel;
import interface_adapter.LogOut.LogOutController;
import interface_adapter.LogOut.LogOutPresenter;
import interface_adapter.UpdateAccount.UpdateAccountController;
import interface_adapter.UpdateAccount.UpdateAccountViewModel;
import interface_adapter.ScanItem.ScanItemViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CreateAccount.CreateAccountDataAccessInterface;
import use_case.Dashboard.DashboardDataAccessInterface;
import use_case.Dashboard.DashboardInputBoundary;
import use_case.Dashboard.DashboardInteractor;
import use_case.Dashboard.DashboardOutputBoundary;
import use_case.DeleteAccount.DeleteAccountDataAccessInterface;
import use_case.DeleteAccount.DeleteAccountInputBoundary;
import use_case.DeleteAccount.DeleteAccountInteractor;
import use_case.DeleteAccount.DeleteAccountOutputBoundary;
import use_case.LogOut.LogOutDataAccessInterface;
import use_case.LogOut.LogOutInputBoundary;
import use_case.LogOut.LogOutInteractor;
import use_case.LogOut.LogOutOutputBoundary;
import use_case.UpdateAccount.UpdateAccountDataAccessInterface;
import use_case.ScanItem.ScanItemDataAccessInterface;
import view.DashboardView;
import view.ScanItemView;

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
                                       DashboardViewModel dashboardViewModel,
                                       DashboardDataAccessInterface userDataAccessObject,
                                       ScanItemViewModel scanItemViewModel,
                                       ScanItemDataAccessInterface scanItemDataAccessObject,
                                       CreateAccountViewModel createAccountViewModel,
                                       DeleteAccountViewModel deleteAccountViewModel,
                                       UpdateAccountViewModel updateAccountViewModel,
                                       GeneratePasswordViewModel generatePasswordViewModel,
                                       CreateAccountDataAccessInterface createAccountDataAccessObject) {
        DashboardController dashboardController = createDashboardUseCase(viewManagerModel, dashboardViewModel,
                userDataAccessObject);
        LogOutController logOutController = LogOutUseCaseFactory.createLogOutUseCase(viewManagerModel, authenticationViewModel,
                (LogOutDataAccessInterface) userDataAccessObject);

        return new DashboardView(dashboardViewModel, dashboardController, logOutController,
                ScanItemUseCaseFactory.createScanItemUseCase(viewManagerModel, scanItemViewModel, scanItemDataAccessObject,
                        dashboardViewModel), scanItemViewModel,
                CreateAccountUseCaseFactory.createAccountUseCase(viewManagerModel, createAccountViewModel,
                        createAccountDataAccessObject, dashboardViewModel), createAccountViewModel, UpdateAccountUseCaseFactory.createUpdateAccountUseCase(viewManagerModel,
                updateAccountViewModel, dashboardViewModel, (UpdateAccountDataAccessInterface) userDataAccessObject), updateAccountViewModel,
                                GeneratePasswordUseCaseFactory.create(viewManagerModel, dashboardViewModel, generatePasswordViewModel),
                                generatePasswordViewModel, createDeleteAccountUseCase(viewManagerModel, deleteAccountViewModel,
                dashboardViewModel, (DeleteAccountDataAccessInterface) userDataAccessObject););
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


    private static DeleteAccountController createDeleteAccountUseCase(ViewManagerModel viewManagerModel,
                                                                      DeleteAccountViewModel deleteAccountViewModel, DashboardViewModel dashboardViewModel,
                                                                      DeleteAccountDataAccessInterface userDataAccessObject) {
        DeleteAccountOutputBoundary deleteAccountPresenter = new DeleteAccountPresenter(viewManagerModel, deleteAccountViewModel, dashboardViewModel);
        DeleteAccountInputBoundary deleteAccountUseCaseInteractor = new DeleteAccountInteractor(userDataAccessObject, deleteAccountPresenter);
        return new DeleteAccountController(deleteAccountUseCaseInteractor);
    }
}
