package app;

import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.Authentication.AuthenticationController;
import interface_adapter.Authentication.AuthenticationPresenter;
import use_case.Authentication.AuthenticationDataAcessInterface;
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
            ViewManagerModel viewManagerModel, AuthenticationViewModel authenticationViewModel, AuthenticationDataAccessInterface userDataAccessObject){

        try{
            AuthenticationController authenticationController = createAuthenticationUseCase(viewManagerModel, authenticationViewModel, userDataAccessObject);
            return new AuthenticationView(authenticationViewModel, authenticationController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static AuthenticationController createAuthenticationUseCase(ViewManagerModel viewManagerModel, AuthenticationViewModel authenticationViewModel,
                                                              AuthenticationDataAccessInterface userDataAccessObject) throws IOException{
        AuthenticationOutputBoundary = new AuthenticationPresenter(viewManagerModel, authenticationViewModel);

        AuthenticationInputBoundary AuthenticationUseCaseInteractor = new AuthenticationUseCaseInteractor(
                userDataAccessObject, AuthenticationOutputBoundary);

        return new AuthenticationController(AuthenticationUseCaseInteractor);
    }

}
