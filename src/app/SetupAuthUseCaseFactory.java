package app;

import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.SetupAuth.SetupAuthController;
import interface_adapter.SetupAuth.SetupAuthPresenter;
import use_case.SetupAuth.*;
import entity.CommonAuthKey;
import entity.AuthKey;
import interface_adapter.*;
import view.SetupAuthView;

import javax.swing.*;
import java.io.IOException;
public class SetupAuthUseCaseFactory {

    private SetupAuthUseCaseFactory() {}

    public static SetupAuthView create(
            ViewManagerModel viewManagerModel, SetupAuthViewModel setupAuthViewModel,
            AuthenticationViewModel authenticationViewModel, SetupAuthDataAccessInterface userDataAccessObject){

        try{
            SetupAuthController setupAuthController = createSetupAuthUseCase(viewManagerModel, setupAuthViewModel,
                    authenticationViewModel, userDataAccessObject);
            return new SetupAuthView(setupAuthViewModel, setupAuthController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SetupAuthController createSetupAuthUseCase(ViewManagerModel viewManagerModel, SetupAuthViewModel setupAuthViewModel,
                                                              AuthenticationViewModel authenticationViewModel,
                                                              SetupAuthDataAccessInterface userDataAccessObject) throws IOException{
        SetupAuthOutputBoundary setupAuthOutputBoundary = new SetupAuthPresenter(viewManagerModel, setupAuthViewModel, authenticationViewModel);

        SetupAuthInputBoundary SetupAuthUseCaseInteractor = new SetupAuthInteractor(
                userDataAccessObject, setupAuthOutputBoundary);

        return new SetupAuthController(SetupAuthUseCaseInteractor);
    }

}
