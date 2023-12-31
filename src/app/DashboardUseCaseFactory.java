package app;

import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.CheckBreach.CheckBreachController;
import interface_adapter.CheckBreach.CheckBreachViewModel;
import interface_adapter.CheckPassQuality.CheckPassQualityController;
import interface_adapter.CheckPassQuality.CheckPassQualityViewModel;
import interface_adapter.CreateAccount.CreateAccountController;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.Dashboard.DashboardController;
import interface_adapter.Dashboard.DashboardPresenter;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.DeleteAccount.DeleteAccountController;
import interface_adapter.DeleteAccount.DeleteAccountPresenter;
import interface_adapter.DeleteAccount.DeleteAccountViewModel;
import interface_adapter.GenerateEmail.GenerateEmailController;
import interface_adapter.GenerateEmail.GenerateEmailViewModel;
import interface_adapter.Generate2FACode.Generate2FACodeController;
import interface_adapter.Generate2FACode.Generate2FACodeViewModel;
import interface_adapter.GeneratePassword.GeneratePasswordController;
import interface_adapter.GeneratePassword.GeneratePasswordViewModel;
import interface_adapter.LogOut.LogOutController;
import interface_adapter.LogOut.LogOutPresenter;
import interface_adapter.ScanItem.ScanItemController;
import interface_adapter.UpdateAccount.UpdateAccountController;
import interface_adapter.UpdateAccount.UpdateAccountViewModel;
import interface_adapter.ScanItem.ScanItemViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CheckBreach.CheckBreachDataAccessInterface;
import use_case.CreateAccount.CreateAccountDataAccessInterface;
import use_case.Dashboard.DashboardDataAccessInterface;
import use_case.Dashboard.DashboardInputBoundary;
import use_case.Dashboard.DashboardInteractor;
import use_case.Dashboard.DashboardOutputBoundary;
import use_case.DeleteAccount.DeleteAccountDataAccessInterface;
import use_case.DeleteAccount.DeleteAccountInputBoundary;
import use_case.DeleteAccount.DeleteAccountInteractor;
import use_case.DeleteAccount.DeleteAccountOutputBoundary;
import use_case.GenerateEmail.GenerateEmailDataAccessInterface;
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
                                       CheckBreachViewModel checkBreachViewModel,
                                       CheckBreachDataAccessInterface checkBreachDataAccessObject,
                                       CreateAccountViewModel createAccountViewModel,
                                       DeleteAccountViewModel deleteAccountViewModel,
                                       UpdateAccountViewModel updateAccountViewModel,
                                       GeneratePasswordViewModel generatePasswordViewModel,
                                       GenerateEmailViewModel generateEmailViewModel,
                                       GenerateEmailDataAccessInterface generateEmailDataAccessObject,
                                       CheckPassQualityViewModel checkPassQualityViewModel,
                                       Generate2FACodeViewModel generate2FACodeViewModel,
                                       CreateAccountDataAccessInterface createAccountDataAccessObject) {
        DashboardController dashboardController = createDashboardUseCase(viewManagerModel, dashboardViewModel,
                userDataAccessObject);
        LogOutController logOutController = LogOutUseCaseFactory.createLogOutUseCase(viewManagerModel, authenticationViewModel,
                (LogOutDataAccessInterface) userDataAccessObject);
        ScanItemController scanItemController = ScanItemUseCaseFactory.createScanItemUseCase(viewManagerModel, scanItemViewModel, scanItemDataAccessObject,
                dashboardViewModel);
        CheckBreachController checkBreachController = CheckBreachUseCaseFactory.createCheckBreachUseCase(viewManagerModel,
                checkBreachViewModel, checkBreachDataAccessObject, dashboardViewModel);
        CheckPassQualityController checkPassQualityController = CheckPassQualityUseCaseFactory.createCheckPassQualityUseCase(generatePasswordViewModel, checkPassQualityViewModel);
        Generate2FACodeController generate2FACodeController = Generate2FACodeUseCaseFactory.createGenerate2FACodeUseCase(viewManagerModel, generate2FACodeViewModel);
        GeneratePasswordController generatePasswordController = GeneratePasswordUseCaseFactory.createGeneratePasswordUseCase(viewManagerModel, dashboardViewModel, generatePasswordViewModel);
        UpdateAccountController updateAccountController = UpdateAccountUseCaseFactory.createUpdateAccountUseCase(viewManagerModel,
                updateAccountViewModel, dashboardViewModel, (UpdateAccountDataAccessInterface) userDataAccessObject);
        CreateAccountController createAccountController = CreateAccountUseCaseFactory.createAccountUseCase(viewManagerModel, createAccountViewModel,
                createAccountDataAccessObject, dashboardViewModel);
        DeleteAccountController deleteAccountController = DeleteAccountUseCaseFactory.createDeleteAccountUseCase(viewManagerModel, deleteAccountViewModel, dashboardViewModel, (DeleteAccountDataAccessInterface) userDataAccessObject);
        GenerateEmailController generateEmailController = GenerateEmailUseCaseFactory.createGenerateEmailUseCase(viewManagerModel, generateEmailViewModel, generateEmailDataAccessObject,
                dashboardViewModel);
        return new DashboardView(dashboardViewModel, dashboardController, logOutController, scanItemController,
                scanItemViewModel, checkBreachController, checkBreachViewModel, createAccountController, createAccountViewModel, updateAccountController, updateAccountViewModel,
                deleteAccountController, deleteAccountViewModel, generatePasswordController, generatePasswordViewModel,
                generateEmailController, generateEmailViewModel,
                checkPassQualityController, checkPassQualityViewModel, generate2FACodeController, generate2FACodeViewModel);
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
}
