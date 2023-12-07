package use_case.DeleteAccount;

import entity.AccountInfo;

public class DeleteAccountInteractor implements DeleteAccountInputBoundary {

    final private DeleteAccountDataAccessInterface dataAccessObject;
    final private DeleteAccountOutputBoundary deleteAccountPresenter;

    /**
     * Constructor method for the use case interactor for the Delete Account use case
     * @param dataAccessObject The data access object which provides access to the stored data required
     * @param deleteAccountPresenter The presenter for the Delete Account use case
     */
    public DeleteAccountInteractor(DeleteAccountDataAccessInterface dataAccessObject,
                                   DeleteAccountOutputBoundary deleteAccountPresenter) {
        this.dataAccessObject = dataAccessObject;
        this.deleteAccountPresenter = deleteAccountPresenter;
    }

    /**
     * Method which contains the logic for the Delete Account use case (interactor) which is triggered to complete use case
     * @param deleteAccountInputData The input data for the Delete Account use case
     */
    public void execute(DeleteAccountInputData deleteAccountInputData) {
        dataAccessObject.deleteAccount(deleteAccountInputData.getTitle(), deleteAccountInputData.getUsername());
        DeleteAccountOutputData outputData = new DeleteAccountOutputData(false, dataAccessObject.getAccounts());
        deleteAccountPresenter.prepareSuccessView(outputData);
    }

    /**
     * Mehtod to alert the presenter for the Delete Account use case to switch the view
     */
    public void switchView() {
        deleteAccountPresenter.switchView();
    }

}
