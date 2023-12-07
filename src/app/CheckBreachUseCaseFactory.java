package app;

import interface_adapter.CheckBreach.CheckBreachController;
import interface_adapter.CheckBreach.CheckBreachPresenter;
import interface_adapter.CheckBreach.CheckBreachViewModel;
import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CheckBreach.CheckBreachDataAccessInterface;
import use_case.CheckBreach.CheckBreachInputBoundary;
import use_case.CheckBreach.CheckBreachInteractor;
import use_case.CheckBreach.CheckBreachOutputBoundary;
import view.CheckBreachView;

public class CheckBreachUseCaseFactory {

    /**
     * Method which creates a new Check Breach view by instantiating all necessary interface adapters
     * @param viewManagerModel the view manager model
     * @param checkBreachViewModel the view model for this view
     * @param checkBreachDataAccessObject the data access object for the Check Breach use case
     * @param dashboardViewModel the view model for the Dashboard View
     * @return a new Check Breach view
     */
    public static CheckBreachView create(ViewManagerModel viewManagerModel, CheckBreachViewModel checkBreachViewModel,
                                         CheckBreachDataAccessInterface checkBreachDataAccessObject,
                                         DashboardViewModel dashboardViewModel) {
        CheckBreachController checkBreachController = createCheckBreachUseCase(viewManagerModel, checkBreachViewModel,
                checkBreachDataAccessObject, dashboardViewModel);
        return new CheckBreachView(checkBreachViewModel, checkBreachController, dashboardViewModel);
    }

    /**
     * Method which creates and returns a new controller object for the Check Breach use case
     * @param viewManagerModel the view manager model
     * @param checkBreachViewModel the view model for this view
     * @param checkBreachDataAccessObject the data access object for the Check Breach use case
     * @param dashboardViewModel the view model for the Dashboard View
     * @return a new controller for the Check Breach use case
     */
    public static CheckBreachController createCheckBreachUseCase(ViewManagerModel viewManagerModel, CheckBreachViewModel checkBreachViewModel,
                                           CheckBreachDataAccessInterface checkBreachDataAccessObject,
                                           DashboardViewModel dashboardViewModel) {
        CheckBreachOutputBoundary checkBreachPresenter = new CheckBreachPresenter(viewManagerModel, checkBreachViewModel,
                dashboardViewModel);
        CheckBreachInputBoundary checkBreachUseCaseInteractor = new CheckBreachInteractor(checkBreachDataAccessObject,
                checkBreachPresenter);
        return new CheckBreachController(checkBreachUseCaseInteractor);
    }

}
