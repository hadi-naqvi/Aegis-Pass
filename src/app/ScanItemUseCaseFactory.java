package app;

import interface_adapter.Authentication.AuthenticationController;
import interface_adapter.Authentication.AuthenticationPresenter;
import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.Dashboard.DashboardController;
import interface_adapter.Dashboard.DashboardPresenter;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.LogOut.LogOutController;
import interface_adapter.LogOut.LogOutPresenter;
import interface_adapter.ScanItem.ScanItemController;
import interface_adapter.ScanItem.ScanItemPresenter;
import interface_adapter.ScanItem.ScanItemViewModel;
import interface_adapter.SetupAuth.SetupAuthViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Authentication.AuthenticationDataAccessInterface;
import use_case.Authentication.AuthenticationInputBoundary;
import use_case.Authentication.AuthenticationInteractor;
import use_case.Authentication.AuthenticationOutputBoundary;
import use_case.Dashboard.DashboardDataAccessInterface;
import use_case.Dashboard.DashboardInputBoundary;
import use_case.Dashboard.DashboardInteractor;
import use_case.Dashboard.DashboardOutputBoundary;
import use_case.LogOut.LogOutDataAccessInterface;
import use_case.LogOut.LogOutInputBoundary;
import use_case.LogOut.LogOutInteractor;
import use_case.LogOut.LogOutOutputBoundary;
import use_case.ScanItem.ScanItemDataAccessInterface;
import use_case.ScanItem.ScanItemInputBoundary;
import use_case.ScanItem.ScanItemInteractor;
import use_case.ScanItem.ScanItemOutputBoundary;
import view.AuthenticationView;
import view.DashboardView;
import view.ScanItemView;

import javax.swing.*;
import java.io.IOException;

public class ScanItemUseCaseFactory {
    /**
     * Method which creates a new ScanItem view by instantiating all necessary interface adapters
     * @param viewManagerModel The view manager model
     * @param scanItemViewModel The view model for the scan item view
     * @param userDataAccessObject The data access object scanItem
     * @return A new ScanItem view
     */
    public static ScanItemView create(
            ViewManagerModel viewManagerModel, ScanItemViewModel scanItemViewModel, ScanItemDataAccessInterface userDataAccessObject,
            DashboardViewModel dashboardViewModel){
        ScanItemController scanItemController = createScanItemUseCase(viewManagerModel, scanItemViewModel,
                userDataAccessObject, dashboardViewModel);
        return new ScanItemView(scanItemViewModel, scanItemController, dashboardViewModel);
    }

    /**
     * Method which creates and returns a new controller object for the Authentication use case
     * @param viewManagerModel The view manager model
     * @param scanItemViewModel The view model for the scanItem view
     * @param userDataAccessObject The data access object for scanItem
     * @return A new controller for the scan Item use case
     */
    public static ScanItemController createScanItemUseCase(ViewManagerModel viewManagerModel,
                                                              ScanItemViewModel scanItemViewModel,
                                                              ScanItemDataAccessInterface userDataAccessObject,
                                                            DashboardViewModel dashboardViewModel) {
        ScanItemOutputBoundary scanItemPresenter = new ScanItemPresenter(viewManagerModel, scanItemViewModel, dashboardViewModel);

        ScanItemInputBoundary scanItemUseCaseInteractor = new ScanItemInteractor(userDataAccessObject,
                scanItemPresenter);

        return new ScanItemController(scanItemUseCaseInteractor);
    }
}
