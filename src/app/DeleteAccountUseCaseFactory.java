package app;

import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.DeleteAccount.DeleteAccountController;
import interface_adapter.DeleteAccount.DeleteAccountPresenter;
import interface_adapter.DeleteAccount.DeleteAccountViewModel;
import interface_adapter.ViewManagerModel;
import use_case.DeleteAccount.DeleteAccountDataAccessInterface;
import use_case.DeleteAccount.DeleteAccountInputBoundary;
import use_case.DeleteAccount.DeleteAccountInteractor;
import use_case.DeleteAccount.DeleteAccountOutputBoundary;

public class DeleteAccountUseCaseFactory {
    /**
     * Factory method which returns a new Delete account controller
     * @param viewManagerModel The view manager model
     * @param deleteAccountViewModel The delete account view model
     * @param dashboardViewModel The dashboard view model
     * @param userDataAccessObject The DAO
     * @return A new controller for the delete account use case
     */
    public static DeleteAccountController createDeleteAccountUseCase(ViewManagerModel viewManagerModel,
                                                                      DeleteAccountViewModel deleteAccountViewModel, DashboardViewModel dashboardViewModel,
                                                                      DeleteAccountDataAccessInterface userDataAccessObject) {
        DeleteAccountOutputBoundary deleteAccountPresenter = new DeleteAccountPresenter(viewManagerModel, deleteAccountViewModel, dashboardViewModel);
        DeleteAccountInputBoundary deleteAccountUseCaseInteractor = new DeleteAccountInteractor(userDataAccessObject, deleteAccountPresenter);
        return new DeleteAccountController(deleteAccountUseCaseInteractor);
    }
}
