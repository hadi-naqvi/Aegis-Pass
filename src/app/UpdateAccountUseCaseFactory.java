package app;

import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.UpdateAccount.UpdateAccountController;
import interface_adapter.UpdateAccount.UpdateAccountPresenter;
import interface_adapter.UpdateAccount.UpdateAccountViewModel;
import interface_adapter.ViewManagerModel;
import use_case.UpdateAccount.*;

public class UpdateAccountUseCaseFactory {
    /**
     * method which creates and returns a new controller for the Update Account use case
     * @param viewManagerModel view manager model
     * @param updateAccountViewModel view model for updateAccount
     * @param dashboardViewModel  view model for dashboard view
     * @param userDataAccessObject DAO for dashboard/updateAccount
     * @return a new controller for the UpdateAccount use case
     */
    public static UpdateAccountController createUpdateAccountUseCase(ViewManagerModel viewManagerModel,
                                                                      UpdateAccountViewModel updateAccountViewModel, DashboardViewModel dashboardViewModel,
                                                                      UpdateAccountDataAccessInterface userDataAccessObject) {
        UpdateAccountOutputBoundary updateAccountPresenter = new UpdateAccountPresenter(viewManagerModel, updateAccountViewModel, dashboardViewModel);

        UpdateAccountInputBoundary updateAccountUseCaseInteractor = new UpdateAccountInteractor(userDataAccessObject, updateAccountPresenter);

        return new UpdateAccountController(updateAccountUseCaseInteractor);
    }
}

