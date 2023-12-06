package app;

import data_access.FileAuthDataAccessObject;
import data_access.FileDashDataAccessObject;
import entity.CommonAccountInfoFactory;
import entity.CommonUserFactory;
import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.CreateAccount.CreateAccountController;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.DeleteAccount.DeleteAccountViewModel;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.ViewManagerModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
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
        CreateAccountViewModel createAccountViewModel = new CreateAccountViewModel();
        DeleteAccountViewModel deleteAccountViewModel = new DeleteAccountViewModel();

        FileAuthDataAccessObject authDataAccessObject;
        FileDashDataAccessObject dashDataAccessObject;

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
                dashboardViewModel, createAccountViewModel, deleteAccountViewModel, dashDataAccessObject);
        views.add(dashboardView, dashboardView.viewName);


        viewManagerModel.setActiveView(setupAuthView.viewName);

        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
