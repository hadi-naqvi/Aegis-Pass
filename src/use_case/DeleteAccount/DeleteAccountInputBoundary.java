package use_case.DeleteAccount;

public interface DeleteAccountInputBoundary {

    /**
     * Method which contains the logic for the Delete Account use case (interactor) which is triggered to complete use case
     * @param deleteAccountInputData The input data for the use case interactor
     */
    void execute(DeleteAccountInputData deleteAccountInputData);

    void switchView();

}
