package app;

import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.CreateAccount.CreateAccountController;
import interface_adapter.CreateAccount.CreateAccountPresenter;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.LogOut.LogOutController;
import interface_adapter.LogOut.LogOutPresenter;
import interface_adapter.ViewManagerModel;
import use_case.CreateAccount.CreateAccountDataAccessInterface;
import use_case.CreateAccount.CreateAccountInputBoundary;
import use_case.CreateAccount.CreateAccountInteractor;
import use_case.CreateAccount.CreateAccountOutputBoundary;
import use_case.LogOut.LogOutDataAccessInterface;
import use_case.LogOut.LogOutInputBoundary;
import use_case.LogOut.LogOutInteractor;
import use_case.LogOut.LogOutOutputBoundary;
import view.CreateAccountView;

    public class CreateAccountUseCaseFactory {
        /**
         * Method which creates a new ScanItem view by instantiating all necessary interface adapters
         * @param viewManagerModel The view manager model
         * @param createAccountViewModel The view model for the create account view
         * @param userDataAccessObject The data access object scanItem
         * @return A new ScanItem view
         */
        public static CreateAccountView create(
                ViewManagerModel viewManagerModel, CreateAccountViewModel createAccountViewModel,
                CreateAccountDataAccessInterface userDataAccessObject, DashboardViewModel dashboardViewModel){
            CreateAccountController createAccountController = createAccountUseCase(viewManagerModel, createAccountViewModel,
                    userDataAccessObject, dashboardViewModel);
            return new CreateAccountView(dashboardViewModel, createAccountViewModel, createAccountController);
        }

        /**
         * Method which creates and returns a new controller object for the Authentication use case
         * @param viewManagerModel The view manager model
         * @param createAccountViewModel The view model for the Create Account view
         * @param userDataAccessObject The data access object for scanItem
         * @param dashboardViewModel The view model for the Dashboard View
         * @return A new controller for the scan Item use case
         */
        public static CreateAccountController createAccountUseCase(ViewManagerModel viewManagerModel,
                                                                CreateAccountViewModel createAccountViewModel,
                                                                CreateAccountDataAccessInterface userDataAccessObject,
                                                                DashboardViewModel dashboardViewModel) {
            CreateAccountOutputBoundary createAccountPresenter = new CreateAccountPresenter(viewManagerModel, createAccountViewModel, dashboardViewModel);

            CreateAccountInputBoundary createAccountInteractor = new CreateAccountInteractor(userDataAccessObject,
                    createAccountPresenter);

            return new CreateAccountController(createAccountInteractor);
        }
    }
