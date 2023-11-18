package use_case.SetupAuth;

import entity.CommonAuthKey;

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
        if (!setupAuthInputData.getPassword().equals(setupAuthInputData.getRepeatPassword())) {
            setupAuthPresenter.prepareFailView("Passwords do not match");
        }
        else {
            CommonAuthKey authKey = new CommonAuthKey(setupAuthInputData.getPassword());
            authDataAccessObject.save(authKey);

            SetupAuthOutputData setupAuthOutputData = new SetupAuthOutputData(true);
            setupAuthPresenter.prepareSuccessView(setupAuthOutputData);
        }
    }
}