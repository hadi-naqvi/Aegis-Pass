package app;

import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.DisplayDash.DashboardViewModel;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.Authentication.AuthenticationController;
import interface_adapter.Authentication.AuthenticationPresenter;
import use_case.Authentication.AuthenticationDataAccessInterface;
import use_case.Authentication.*;
import entity.CommonAuthKey;
import entity.AuthKey;
import interface_adapter.*;
import view.AuthenticationView;
import view.SetupAuthView;

import javax.swing.*;
import java.io.IOException;
public class AuthenticationUseCaseFactory {

    private AuthenticationUseCaseFactory() {}

    public static AuthenticationView create(
            ViewManagerModel viewManagerModel, AuthenticationViewModel authenticationViewModel, DashboardViewModel dashboardViewModel, AuthenticationDataAccessInterface userDataAccessObject){

        try{
            AuthenticationController authenticationController = createAuthenticationUseCase(viewManagerModel, authenticationViewModel, dashboardViewModel, userDataAccessObject);
            return new AuthenticationView(authenticationViewModel, authenticationController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static AuthenticationController createAuthenticationUseCase(ViewManagerModel viewManagerModel, AuthenticationViewModel authenticationViewModel, DashboardViewModel dashboardViewModel,
                                                              AuthenticationDataAccessInterface userDataAccessObject) throws IOException{
        AuthenticationOutputBoundary authenticationPresenter = new AuthenticationPresenter(viewManagerModel, authenticationViewModel, dashboardViewModel);

        AuthenticationInputBoundary AuthenticationUseCaseInteractor = new AuthenticationInteractor(
                userDataAccessObject, authenticationPresenter);

        return new AuthenticationController(AuthenticationUseCaseInteractor);
    }

}
