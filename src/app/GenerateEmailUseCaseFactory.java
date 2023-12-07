package app;

import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.GenerateEmail.GenerateEmailController;
import interface_adapter.GenerateEmail.GenerateEmailPresenter;
import interface_adapter.GenerateEmail.GenerateEmailViewModel;
import interface_adapter.ScanItem.ScanItemController;
import interface_adapter.ScanItem.ScanItemPresenter;
import interface_adapter.ScanItem.ScanItemViewModel;
import interface_adapter.ViewManagerModel;
import use_case.GenerateEmail.GenerateEmailDataAccessInterface;
import use_case.GenerateEmail.GenerateEmailInputBoundary;
import use_case.GenerateEmail.GenerateEmailInteractor;
import use_case.GenerateEmail.GenerateEmailOutputBoundary;
import use_case.ScanItem.ScanItemDataAccessInterface;
import use_case.ScanItem.ScanItemInputBoundary;
import use_case.ScanItem.ScanItemInteractor;
import use_case.ScanItem.ScanItemOutputBoundary;
import view.GenerateEmailView;
import view.ScanItemView;

public class GenerateEmailUseCaseFactory {
    /**
     * Method which creates a new ScanItem view by instantiating all necessary interface adapters
     * @param viewManagerModel The view manager model
     * @param generateEmailViewModel The view model for the generate email view
     * @param userDataAccessObject The data access object scanItem
     * @return A new ScanItem view
     */
    public static GenerateEmailView create(
            ViewManagerModel viewManagerModel, GenerateEmailViewModel generateEmailViewModel, GenerateEmailDataAccessInterface userDataAccessObject,
            DashboardViewModel dashboardViewModel){
        GenerateEmailController generateEmailController = createGenerateEmailUseCase(viewManagerModel, generateEmailViewModel,
                userDataAccessObject, dashboardViewModel);
        return new GenerateEmailView(generateEmailViewModel, generateEmailController, dashboardViewModel);
    }

    /**
     * Method which creates and returns a new controller object for the Scan Item use case
     * @param viewManagerModel The view manager model
     * @param generateEmailViewModel The view model for the generateEmail view
     * @param userDataAccessObject The data access object for scanItem
     * @return A new controller for the Scan Item use case
     */
    public static GenerateEmailController createGenerateEmailUseCase(ViewManagerModel viewManagerModel,
                                                                     GenerateEmailViewModel generateEmailViewModel,
                                                                     GenerateEmailDataAccessInterface userDataAccessObject,
                                                                     DashboardViewModel dashboardViewModel) {
        GenerateEmailOutputBoundary generateEmailPresenter = new GenerateEmailPresenter(viewManagerModel, generateEmailViewModel, dashboardViewModel);

        GenerateEmailInputBoundary generateEmailUseCaseInteractor = new GenerateEmailInteractor(userDataAccessObject,
                generateEmailPresenter);

        return new GenerateEmailController(generateEmailUseCaseInteractor);
    }
}
