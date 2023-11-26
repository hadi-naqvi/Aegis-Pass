package use_case.SetupAuth;

import entity.CommonUser;

public class SetupAuthInteractor implements SetupAuthInputBoundary {
    private final SetupAuthDataAccessInterface authDataAccessObject;
    private final SetupAuthOutputBoundary setupAuthPresenter;

    /**
     * Consturctor method for the SetupAuthInteractor
     * @param authDataAccessInterface The data access object for the auth key
     * @param setupAuthPresenter The presenter object for the SetupAuth use case (has the success/fail views)
     */
    public SetupAuthInteractor(SetupAuthDataAccessInterface authDataAccessInterface, SetupAuthOutputBoundary setupAuthPresenter) {
        this.authDataAccessObject = authDataAccessInterface;
        this.setupAuthPresenter = setupAuthPresenter;
    }

    /**
     * Method which contains the logic for the SetupAuth use case (interactor) which is triggered to complete use case
     * @param setupAuthInputData The input data for the use case interactor
     */
    @Override
    public void execute(SetupAuthInputData setupAuthInputData) {
        if (authDataAccessObject.existsByName(setupAuthInputData.getUsername())){
            setupAuthPresenter.prepareFailView("The username " + setupAuthInputData.getUsername() + " has been taken");
        }
        else if (!setupAuthInputData.getPassword().equals(setupAuthInputData.getRepeatPassword())) {
            System.out.println(setupAuthInputData.getUsername());
            System.out.println(setupAuthInputData.getPassword());
            System.out.println(setupAuthInputData.getRepeatPassword());
            setupAuthPresenter.prepareFailView("Passwords do not match.");
        }
        else if (setupAuthInputData.getPassword().isEmpty()) {
            setupAuthPresenter.prepareFailView("You have not entered a password.");
        }
        else {
            CommonUser user = new CommonUser(setupAuthInputData.getUsername(), setupAuthInputData.getPassword());
            authDataAccessObject.save(user);

            SetupAuthOutputData setupAuthOutputData = new SetupAuthOutputData(true);
            setupAuthPresenter.prepareSuccessView(setupAuthOutputData);
        }
    }
}
