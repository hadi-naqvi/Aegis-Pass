package use_case.UpdateAccount;

public interface UpdateAccountInputBoundary {
    /**
     * Method which contains the logic for the UpdateAccount use case (interactor) which is triggered to complete use case
     * @param updateAccountInputData The input data for the use case interactor
     */
    void execute(UpdateAccountInputData updateAccountInputData);

    void switchView();
}
