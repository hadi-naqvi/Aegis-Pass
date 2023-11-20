package interface_adapter.Authentication;

import use_case.Authentication.AuthenticationInputBoundary;
import use_case.Authentication.AuthenticationInputData;

public class AuthenticationController {
    public final AuthenticationInputBoundary authenticationInteractor;

    /**
     * Constructor method for the controller for the authentication use case
     * @param authenticationInteractor The use case interactor object for the authentication use case
     */
    public AuthenticationController(AuthenticationInputBoundary authenticationInteractor) {
        this.authenticationInteractor = authenticationInteractor;
    }

    /**
     * Method which is executed/triggered when the user enters their authentication password
     * @param password The password/authentication key being entered by the user
     */
    public void execute(String password) {
        AuthenticationInputData authenticationInputData = new AuthenticationInputData(password);
        authenticationInteractor.execute(authenticationInputData);
    }
}