package interface_adapter.SetupAuth;

import use_case.SetupAuth.SetupAuthInputBoundary;
import use_case.SetupAuth.SetupAuthInputData;

public class SetupAuthController {
    public final SetupAuthInputBoundary setupAuthInteractor;

    /**
     * Constructor method for the SetupAuth use case's controller
     * @param setupAuthInteractor The SetupAuth use case interactor object
     */
    public SetupAuthController(SetupAuthInputBoundary setupAuthInteractor) {
        this.setupAuthInteractor = setupAuthInteractor;
    }

    /**
     * Method which is executed/triggered when the user completes the authentication key setup
     * @param username The authentication username
     * @param password The authentication password
     * @param repeatedPassword The confirmed/repeated authentication password
     */
    public void execute(String username, String password, String repeatedPassword) {
        SetupAuthInputData setupAuthInputData = new SetupAuthInputData(username, password, repeatedPassword);
        setupAuthInteractor.execute(setupAuthInputData);
    }

    public void switchViews(){
        setupAuthInteractor.switchViews();
    }
}
