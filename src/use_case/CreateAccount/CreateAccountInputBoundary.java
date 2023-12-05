package use_case.CreateAccount;

public interface CreateAccountInputBoundary {
    /**
     * Method which contains the logic for the CreateAccount use case (interactor) which is triggered to complete use case
     * @param createAccountInputData The input data for the use case interactor
     */
    void execute(CreateAccountInputData createAccountInputData);

    void switchView();
}
