package app;

import data_access.FileAuthDataAccessObject;
import data_access.FileDashDataAccessObject;
import data_access.FileGenEmailDataAccessObject;
import data_access.FileScanDataAccessObject;
import entity.CommonAccountInfoFactory;
import entity.CommonUserFactory;
import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.CheckPassQuality.CheckPassQualityViewModel;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.DeleteAccount.DeleteAccountViewModel;
import interface_adapter.GenerateEmail.GenerateEmailViewModel;
import interface_adapter.Generate2FACode.Generate2FACodeViewModel;
import interface_adapter.GeneratePassword.GeneratePasswordViewModel;
import interface_adapter.ScanItem.ScanItemViewModel;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.UpdateAccount.UpdateAccountViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CreateAccount.CreateAccountDataAccessInterface;
import use_case.ScanItem.ScanItemDataAccessInterface;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Build the main program window, the main panel containing the
        //various cards, and the layout, and stitch them together.

        //The main application window
        JFrame application = new JFrame("");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(1280, 720);
        application.setResizable(false);

        CardLayout cardLayout = new CardLayout();

        //The various View objects, only one view is visible at a time
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        //Keeps track of the current view being displayed(assuming ViewManagerModel is created
        // and takes correct arguments)
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SetupAuthViewModel setupAuthViewModel = new SetupAuthViewModel();
        AuthenticationViewModel authenticationViewModel = new AuthenticationViewModel();
        DashboardViewModel dashboardViewModel = new DashboardViewModel();
        ScanItemViewModel scanItemViewModel = new ScanItemViewModel();
        CreateAccountViewModel createAccountViewModel = new CreateAccountViewModel();
        DeleteAccountViewModel deleteAccountViewModel = new DeleteAccountViewModel();
        GeneratePasswordViewModel generatePasswordViewModel = new GeneratePasswordViewModel();
        UpdateAccountViewModel updateAccountViewModel = new UpdateAccountViewModel();
        GenerateEmailViewModel generateEmailViewModel = new GenerateEmailViewModel();
        CheckPassQualityViewModel checkPassQualityViewModel = new CheckPassQualityViewModel();
        Generate2FACodeViewModel generate2FACodeViewModel = new Generate2FACodeViewModel();

        FileAuthDataAccessObject authDataAccessObject;
        FileDashDataAccessObject dashDataAccessObject;
        FileScanDataAccessObject scanDataAccessObject;
        CreateAccountDataAccessInterface createDataAccessObject;
        FileGenEmailDataAccessObject genEmailDataAccessObject;

        try {
            authDataAccessObject = new FileAuthDataAccessObject(new CommonUserFactory(),
                    System.getenv("DB_URL"),
                    System.getenv("DB_USERNAME"),
                    System.getenv("DB_PASSWORD"),
                    System.getenv("DB_PEPPER"));
            dashDataAccessObject = new FileDashDataAccessObject(new CommonAccountInfoFactory(),
                    System.getenv("DB_URL"),
                    System.getenv("DB_USERNAME"),
                    System.getenv("DB_PASSWORD"));
            scanDataAccessObject = new FileScanDataAccessObject(
                    System.getenv("VT_APIKEY"));
            createDataAccessObject = dashDataAccessObject;
            genEmailDataAccessObject = new FileGenEmailDataAccessObject();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        SetupAuthView setupAuthView = SetupAuthUseCaseFactory.create(viewManagerModel, setupAuthViewModel, authenticationViewModel,
                authDataAccessObject);
        views.add(setupAuthView, setupAuthView.viewName);

        AuthenticationView authenticationView = AuthenticationUseCaseFactory.create(viewManagerModel,
                authenticationViewModel, dashboardViewModel, setupAuthViewModel,
                authDataAccessObject, dashDataAccessObject);
        views.add(authenticationView, authenticationView.viewName);

        ScanItemView scanItemView = ScanItemUseCaseFactory.create(viewManagerModel, scanItemViewModel, scanDataAccessObject,
                dashboardViewModel);
        views.add(scanItemView, scanItemView.viewName);

        DashboardView dashboardView = DashboardUseCaseFactory.create(viewManagerModel, authenticationViewModel,
                dashboardViewModel, dashDataAccessObject, scanItemViewModel, scanDataAccessObject, createAccountViewModel, deleteAccountViewModel, updateAccountViewModel,
                generatePasswordViewModel, checkPassQualityViewModel, generateEmailViewModel, generateEmailDataAccessObject,
                                                                     generate2FACodeViewModel, createDataAccessObject);
        views.add(dashboardView, dashboardView.viewName);

        viewManagerModel.setActiveView(setupAuthView.viewName);

        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}