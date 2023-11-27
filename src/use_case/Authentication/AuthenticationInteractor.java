package use_case.Authentication;


import use_case.SetupAuth.SetupAuthOutputData;

public class AuthenticationInteractor implements AuthenticationInputBoundary {
    final private AuthenticationDataAccessInterface userDataAccessObject;
    final private AuthenticationOutputBoundary authenticationPresenter;

    /**
     * Constructor method for the use case interactor for the authentication use case
     * @param userDataAccessObject The data access object which provides access to the stored data required
     * @param authenticationPresenter The presenter for the authentication use case
     */
    public AuthenticationInteractor(AuthenticationDataAccessInterface userDataAccessObject,
                                    AuthenticationOutputBoundary authenticationPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.authenticationPresenter = authenticationPresenter;
    }

    /**
     * Method which contains the logic for the authentication use case (interactor) which is triggered to complete use case
     * @param authenticationInputData
     */
    @Override
    public void execute(AuthenticationInputData authenticationInputData) {
        if (!(userDataAccessObject.validate(authenticationInputData.getUsername(),
                authenticationInputData.getPassword()))) {
            authenticationPresenter.prepareFailView("Passwords is incorrect.");
        }
        else {
            AuthenticationOutputData authenticationOutputData = new AuthenticationOutputData(true);
            authenticationPresenter.prepareSuccessView(authenticationOutputData);
        }
    }
}