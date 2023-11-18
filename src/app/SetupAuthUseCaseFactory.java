package app;

import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.SetupAuth.SetupAuthController;
import interface_adapter.SetupAuth.SetupAuthPresenter;
import use_case.SetupAuth.SetupAuthDataAccessInterface;
import entity.CommonAuthKey;
import entity.AuthKey;
import interface_adapter.*;
import use_case.SetupAuth.SetupAuthInputBoundary;
import use_case.SetupAuth.SetupAuthUseCaseInteractor;
import use_case.SetupAuth.SetupAuthOutputBoundary;
import view.SetupAuthView;

import javax.swing.*;
import java.io.IOException;
public class SetupAuthUseCaseFactory {

    private SetupAuthUseCaseFactory() {}

    public static SetupAuthView create(
            ViewManagerModel viewManagerModel, SetupAuthViewModel setupAuthViewModel, SetupAuthDataAccessInterface userDataAccessObject){

        try{
            SetupAuthController setupAuthController = createSetupAuthUseCase(viewManagerModel, setupAuthViewModel, userDataAccessObject);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }
}
