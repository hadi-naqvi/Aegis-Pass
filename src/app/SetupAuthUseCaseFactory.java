package app;

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
            ViewManagerModel viewManagerModel, SetupAuthViewModel setupAuthViewModel, SetupAuthDataAccessInterface userDataAccessObject){

        try{
            SetupAuthController setupAuthController = createSetupAuthUseCase(viewManagerModel, setupAuthViewModel, userDataAccessObject);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SetupAuthController createSetupAuthUseCase(ViewManagerModel viewManagerModel, SetupAuthViewModel setupAuthViewModel,
                                                              SetupAuthDataAccessInterface userDataAccessObject) throws IOException{
        SetupAuthOutputBoundary setupAuthOutputBoundary = new SetupAuthPresenter(viewManagerModel, setupAuthViewModel);

        SetupAuthUseCaseFactory setupAuthUseCaseFactory = new SetupAuthUseCaseFactory();

        SetupAuthInputBoundary SetupAuthUseCaseInteractor = new SetupAuthUseCaseInteractor(
                userDataAccessObject, setupAuthOutputBoundary, setupAuthUseCaseFactory);

        return new SetupAuthController(SetupAuthUseCaseInteractor);
    }

}
