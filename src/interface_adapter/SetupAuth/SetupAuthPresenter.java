package interface_adapter.SetupAuth;

import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.ViewManagerModel;
import use_case.SetupAuth.SetupAuthOutputBoundary;
import use_case.SetupAuth.SetupAuthOutputData;

public class SetupAuthPresenter implements SetupAuthOutputBoundary {
    private final SetupAuthViewModel setupAuthViewModel;
    private final AuthenticationViewModel authenticationViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor method for the presenter for the SetupAut use case
     * @param viewManagerModel The view manager's model
     * @param setupAuthViewModel The SetupAuth view's view model
     * @param authenticationViewModel The Authentication view's view model
     */
    public SetupAuthPresenter(ViewManagerModel viewManagerModel,
                              SetupAuthViewModel setupAuthViewModel,
                              AuthenticationViewModel authenticationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.setupAuthViewModel = setupAuthViewModel;
        this.authenticationViewModel = authenticationViewModel;
    }

    /**
     * Method which updates the view manager model to display the authentication screen after setup is complete
     * @param setupAuthOutputData The SetupAuth use case output data
     */
    @Override
    public void prepareSuccessView(SetupAuthOutputData setupAuthOutputData) {
        viewManagerModel.setActiveView(authenticationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Method which updates the view manager model to display the failed SetupAuth screen
     * @param error The error message which explains why SetupAuth failed
     */
    @Override
    public void prepareFailView(String error) {
        SetupAuthState setupAuthState = setupAuthViewModel.getState();
        setupAuthState.setPasswordError(error);
        setupAuthViewModel.firePropertyChanged();
    }
}
