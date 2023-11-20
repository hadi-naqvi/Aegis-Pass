package app;

import data_access.FileAuthDataAccessObject;
import entity.CommonAuthKey;
import entity.CommonAuthKeyFactory;
import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.ViewManagerModel;
import use_case.SetupAuth.SetupAuthDataAccessInterface;
import view.AuthenticationView;
import view.SetupAuthView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        //Build the main program window, the main panel containing the
        //various cards, and the layout, and stitch them together.

        //The main application window
        JFrame application = new JFrame("");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

        FileAuthDataAccessObject userDataAccessObject;

        try {
            userDataAccessObject = new FileAuthDataAccessObject("Database/Authentication.csv", new CommonAuthKeyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SetupAuthView setupAuthView = SetupAuthUseCaseFactory.create(viewManagerModel, setupAuthViewModel, authenticationViewModel,
                userDataAccessObject);
        views.add(setupAuthView, setupAuthView.viewName);

        AuthenticationView authenticationView = AuthenticationUseCaseFactory.create(viewManagerModel, setupAuthViewModel, authenticationViewModel,
                userDataAccessObject);
        views.add(authenticationView, authenticationView.viewName);

        viewManagerModel.setActiveView(setupAuthView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
