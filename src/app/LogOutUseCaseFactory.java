package app;

import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.LogOut.LogOutController;
import interface_adapter.LogOut.LogOutPresenter;
import interface_adapter.ViewManagerModel;
import use_case.LogOut.LogOutDataAccessInterface;
import use_case.LogOut.LogOutInputBoundary;
import use_case.LogOut.LogOutInteractor;
import use_case.LogOut.LogOutOutputBoundary;

    public class LogOutUseCaseFactory {
        /**
         * Method which creates and returns a new controller object for the Log Out use case
         * @param viewManagerModel The view manager model
         * @param authenticationViewModel The view model for the Authentication view
         * @param userDataAccessObject The data access object for scanItem
         * @return A new controller for the scan Item use case
         */
        public static LogOutController createLogOutUseCase(ViewManagerModel viewManagerModel,
                                                            AuthenticationViewModel authenticationViewModel,
                                                           LogOutDataAccessInterface userDataAccessObject) {
            LogOutOutputBoundary logOutPresenter = new LogOutPresenter(viewManagerModel, authenticationViewModel);

            LogOutInputBoundary logOutInteractor = new LogOutInteractor(userDataAccessObject,
                    logOutPresenter);

            return new LogOutController(logOutInteractor);
        }
    }
