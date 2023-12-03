package interface_adapter.LogOut;

import interface_adapter.Authentication.AuthenticationState;
import interface_adapter.Authentication.AuthenticationViewModel;
import interface_adapter.ViewManagerModel;
import use_case.LogOut.LogOutOutputBoundary;
import use_case.LogOut.LogOutOutputData;

public class LogOutPresenter implements LogOutOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final AuthenticationViewModel authenticationViewModel;

    /**
     * Constructor method for the LogOut use case's presenter
     * @param viewManagerModel The view manager model
     * @param authenticationViewModel The authentication view model
     */
    public LogOutPresenter(ViewManagerModel viewManagerModel, AuthenticationViewModel authenticationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.authenticationViewModel = authenticationViewModel;
    }

    /**
     * Method which prepares the success view for the logout use case
     *
     * @param logOutOutputData The output data for the use case
     */
    @Override
    public void prepareSuccessView(LogOutOutputData logOutOutputData) {
        AuthenticationState state = authenticationViewModel.getState();
        state.setUsername("");
        state.setPassword("");
        authenticationViewModel.setState(state);

        viewManagerModel.setActiveView(authenticationViewModel.getViewName());
        authenticationViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Method which prepares the fail view for the logout use case
     *
     * @param error The error for logout use case if it fails
     */
    @Override
    public void prepareFailView(String error) {

    }
}
