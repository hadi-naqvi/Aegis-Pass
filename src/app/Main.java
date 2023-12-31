package app;

import data_access.*;
import entity.CommonAccountInfoFactory;
import entity.CommonUserFactory;
import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.CheckBreach.CheckBreachViewModel;
import interface_adapter.CheckPassQuality.CheckPassQualityViewModel;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.DeleteAccount.DeleteAccountViewModel;
import interface_adapter.Generate2FACode.Generate2FACodeViewModel;
import interface_adapter.GenerateEmail.GenerateEmailViewModel;
import interface_adapter.GeneratePassword.GeneratePasswordViewModel;
import interface_adapter.ScanItem.ScanItemViewModel;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.UpdateAccount.UpdateAccountViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CreateAccount.CreateAccountDataAccessInterface;
import view.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        //Build the main program window, the main panel containing the
        //various cards, and the layout, and stitch them together.

        //The main application window
        JFrame application = new JFrame("");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(1280, 720);
        application.setTitle("Aegis Pass");
        application.setIconImage(ImageIO.read(new File("src/assets/icon.png")));

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
        CheckBreachViewModel checkBreachViewModel = new CheckBreachViewModel();
        CreateAccountViewModel createAccountViewModel = new CreateAccountViewModel();
        DeleteAccountViewModel deleteAccountViewModel = new DeleteAccountViewModel();
        GeneratePasswordViewModel generatePasswordViewModel = new GeneratePasswordViewModel();
        UpdateAccountViewModel updateAccountViewModel = new UpdateAccountViewModel();
        GenerateEmailViewModel generateEmailViewModel = new GenerateEmailViewModel();
        CheckPassQualityViewModel checkPassQualityViewModel = new CheckPassQualityViewModel();
        Generate2FACodeViewModel generate2FACodeViewModel = new Generate2FACodeViewModel();

        AuthDataAccessObject authDataAccessObject;
        DashDataAccessObject dashDataAccessObject;
        ScanDataAccessObject scanDataAccessObject;
        BreachDataAccessObject breachDataAccessObject;
        CreateAccountDataAccessInterface createDataAccessObject;
        GenEmailDataAccessObject genEmailDataAccessObject;

        try {
            authDataAccessObject = new AuthDataAccessObject(new CommonUserFactory(),
                    System.getenv("DB_URL"),
                    System.getenv("DB_USERNAME"),
                    System.getenv("DB_PASSWORD"),
                    System.getenv("DB_PEPPER"));
            dashDataAccessObject = new DashDataAccessObject(new CommonAccountInfoFactory(),
                    System.getenv("DB_URL"),
                    System.getenv("DB_USERNAME"),
                    System.getenv("DB_PASSWORD"));
            scanDataAccessObject = new ScanDataAccessObject(
                    System.getenv("VT_APIKEY"));
            breachDataAccessObject = new BreachDataAccessObject(System.getenv("PWN_APIKEY"));
            createDataAccessObject = dashDataAccessObject;
            genEmailDataAccessObject = new GenEmailDataAccessObject();
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

        DashboardView dashboardView = DashboardUseCaseFactory.create(viewManagerModel, authenticationViewModel,
                dashboardViewModel, dashDataAccessObject, scanItemViewModel, scanDataAccessObject, checkBreachViewModel, breachDataAccessObject,
                createAccountViewModel, deleteAccountViewModel, updateAccountViewModel,
                generatePasswordViewModel, generateEmailViewModel, genEmailDataAccessObject, checkPassQualityViewModel,
                                                                     generate2FACodeViewModel, createDataAccessObject);
        views.add(dashboardView, dashboardView.viewName);

        viewManagerModel.setActiveView(setupAuthView.viewName);

        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}