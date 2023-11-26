package app;

import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.SetupAuth.SetupAuthController;
import interface_adapter.SetupAuth.SetupAuthPresenter;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.ViewManagerModel;
import use_case.SetupAuth.SetupAuthDataAccessInterface;
import use_case.SetupAuth.SetupAuthInputBoundary;
import use_case.SetupAuth.SetupAuthInteractor;
import use_case.SetupAuth.SetupAuthOutputBoundary;
import view.SetupAuthView;

import javax.swing.*;
import java.io.IOException;
public class SetupAuthUseCaseFactory {
    /**
     * Method which creates a new SetupAuth view by instantiating all necessary interface adapters
     * @param viewManagerModel The view manager model
     * @param setupAuthViewModel The view model for the setup auth view
     * @param authenticationViewModel The view model for the authentication view
     * @param userDataAccessObject The data access object for authentication/setupAuth
     * @return A new setup auth view
     */
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

    /**
     * Method which creates and returns a new controller object for the Authentication use case
     * @param viewManagerModel The view manager model
     * @param setupAuthViewModel The view model for the setup auth view
     * @param authenticationViewModel The view model for the authentication view
     * @param userDataAccessObject The data access object for authentication/setupAuth
     * @return A new controller for the setup auth use case
     */
    private static SetupAuthController createSetupAuthUseCase(ViewManagerModel viewManagerModel, SetupAuthViewModel setupAuthViewModel,
                                                              AuthenticationViewModel authenticationViewModel,
                                                              SetupAuthDataAccessInterface userDataAccessObject) throws IOException{
        SetupAuthOutputBoundary setupAuthOutputBoundary = new SetupAuthPresenter(viewManagerModel, setupAuthViewModel, authenticationViewModel);

        SetupAuthInputBoundary SetupAuthUseCaseInteractor = new SetupAuthInteractor(
                userDataAccessObject, setupAuthOutputBoundary);

        return new SetupAuthController(SetupAuthUseCaseInteractor);
    }

}
