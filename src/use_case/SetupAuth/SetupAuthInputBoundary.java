package use_case.SetupAuth;
public interface SetupAuthInputBoundary {
    /**
     * Method which contains the logic for the SetupAuth use case (interactor) which is triggered to complete use case
     * @param setupAuthInputData The input data for the use case interactor
     */
    void execute(SetupAuthInputData setupAuthInputData);

    /**
     * Method which contains logic for switching views which is triggered if user already has an account
     */
    void switchViews();
}
