package use_case.UpdateAccount;

import entity.AccountInfo;

public class UpdateAccountInteractor implements  UpdateAccountInputBoundary{
    final private UpdateAccountDataAccessInterface userDataAccessObject;
    final private UpdateAccountOutputBoundary updateAccountPresenter;

    /**
     * Constructor method for the use case interactor for the UpdateAccount use case
     * @param userDataAccessObject The data access object which provides access to the stored data required
     * @param updateAccountPresenter The presenter for the UpdateAccount use case
     */
    public UpdateAccountInteractor(UpdateAccountDataAccessInterface userDataAccessObject,
                                   UpdateAccountOutputBoundary updateAccountPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.updateAccountPresenter = updateAccountPresenter;
    }

    /**
     * Method which contains the logic for the UpdateAccount use case (interactor) which is triggered to complete use case
     * @param updateAccountInputData input data for the UpdateAccount use case
     */
    @Override
    public void execute(UpdateAccountInputData updateAccountInputData) {
        boolean duplicate = isDuplicateAccount(updateAccountInputData.getTitle(), updateAccountInputData.getUsername());
        if (duplicate){
            updateAccountPresenter.prepareFailView("Duplicate Account found");
        } else if (updateAccountInputData.getAccountIndex() == -1) {
            updateAccountPresenter.prepareFailView("No account selected");
        } else {
            userDataAccessObject.updateAccount(updateAccountInputData.getAccountIndex(), updateAccountInputData.getTitle(), updateAccountInputData.getUsername(),
                    updateAccountInputData.getPassword(), updateAccountInputData.getSecretKey(), updateAccountInputData.getURL(),
                    updateAccountInputData.getIconURL(), updateAccountInputData.getDate(), updateAccountInputData.getNotes());
            UpdateAccountOutputData updateAccoutOutputData = new UpdateAccountOutputData(true, userDataAccessObject.getAccounts());
            updateAccountPresenter.prepareSuccessView(updateAccoutOutputData);
        }
    }

    /**
     * Check if an account with the same title and username combination already exists
     *
     * @param title    The title of the account to be checked
     * @param username The username of the account to be checked
     * @return true if a duplicate account is found, false otherwise
     */
    public boolean isDuplicateAccount(String title, String username) {
        // Check for duplicate title and username combination
        for (AccountInfo account : userDataAccessObject.getAccounts()) {
            if (account.getTitle().equals(title) && account.getUsername().equals(username)) {
                return true; // Duplicate title and username combination found
            }
        }

        return false; // No duplicate found
    }

    public void switchView(){
        updateAccountPresenter.switchView();
    }
}
