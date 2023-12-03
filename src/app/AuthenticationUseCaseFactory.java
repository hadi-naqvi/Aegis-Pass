package app;

import interface_adapter.Authentication.AuthenticationController;
import interface_adapter.Authentication.AuthenticationPresenter;
import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.DisplayDash.DashboardViewModel;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Authentication.AuthenticationDataAccessInterface;
import use_case.Authentication.AuthenticationInputBoundary;
import use_case.Authentication.AuthenticationInteractor;
import use_case.Authentication.AuthenticationOutputBoundary;
import view.AuthenticationView;

import javax.swing.*;
import java.io.IOException;
public class AuthenticationUseCaseFactory {
    /**
     * Method which creates a new Authentication view by instantiating all necessary interface adapters
     * @param viewManagerModel The view manager model
     * @param authenticationViewModel The view model for the authentication view
     * @param dashboardViewModel The view model for the dashboard view
     * @param userDataAccessObject The data access object authentication/setupAuth
     * @return A new Authentication view
     */
    public static AuthenticationView create(
            ViewManagerModel viewManagerModel,
            AuthenticationViewModel authenticationViewModel,
            SetupAuthViewModel setupAuthViewModel,
            DashboardViewModel dashboardViewModel,
            AuthenticationDataAccessInterface userDataAccessObject){

        try{
            AuthenticationController authenticationController = createAuthenticationUseCase(viewManagerModel, authenticationViewModel, setupAuthViewModel, dashboardViewModel, userDataAccessObject);
            return new AuthenticationView(authenticationViewModel, authenticationController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    /**
     * Method which creates and returns a new controller object for the Authentication use case
     * @param viewManagerModel The view manager model
     * @param authenticationViewModel The view model for the authentication view
     * @param dashboardViewModel The view model for the dashboard view
     * @param userDataAccessObject The data access object for authentication/setupAuth
     * @return A new controller for the Authentication use case
     */
    private static AuthenticationController createAuthenticationUseCase(ViewManagerModel viewManagerModel,
                                                                        AuthenticationViewModel authenticationViewModel,
                                                                        SetupAuthViewModel setupAuthViewModel,
                                                                        DashboardViewModel dashboardViewModel,
                                                                        AuthenticationDataAccessInterface userDataAccessObject) throws IOException{
        AuthenticationOutputBoundary authenticationPresenter = new AuthenticationPresenter(viewManagerModel,
                        authenticationViewModel,
                dashboardViewModel,
                setupAuthViewModel);

        AuthenticationInputBoundary AuthenticationUseCaseInteractor = new AuthenticationInteractor(
                userDataAccessObject, authenticationPresenter);

        return new AuthenticationController(AuthenticationUseCaseInteractor);
    }

}
