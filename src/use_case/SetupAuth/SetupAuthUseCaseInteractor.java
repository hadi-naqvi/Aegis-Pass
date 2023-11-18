package use_case.SetupAuth;

import entity.CommonAuthKey;

public class SetupAuthUseCaseInteractor implements SetupAuthInputBoundary {
    private final SetupAuthDataAccessInterface authDataAccessObject;
    private final SetupAuthOutputBoundary setupAuthPresenter;
    public SetupAuthUseCaseInteractor(SetupAuthDataAccessInterface authDataAccessInterface, SetupAuthOutputBoundary setupAuthPresenter) {
        this.authDataAccessObject = authDataAccessInterface;
        this.setupAuthPresenter = setupAuthPresenter;
    }

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
