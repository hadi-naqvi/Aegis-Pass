package use_case.CreateAccount;

import entity.AccountInfo;

public class CreateAccountInteractor implements CreateAccountInputBoundary{
    final private CreateAccountDataAccessInterface userDataAccessObject;
    final private CreateAccountOutputBoundary createAccountPresenter;

    /**
     * Constructor method for the use case interactor for the CreateAccount use case
     * @param userDataAccessObject The data access object which provides access to the stored data required
     * @param createAccountPresenter The presenter for the CreateAccount use case
     */
    public CreateAccountInteractor(CreateAccountDataAccessInterface userDataAccessObject,
                               CreateAccountOutputBoundary createAccountPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.createAccountPresenter = createAccountPresenter;
    }

    /**
     * Method which contains the logic for the CreateAccount use case (interactor) which is triggered to complete use case
     * @param createAccountInputData input data for the CreateAccount use case
     */
    @Override
    public void execute(CreateAccountInputData createAccountInputData) {
        boolean duplicate = isDuplicateAccount(createAccountInputData.getTitle(), createAccountInputData.getUsername());
        if (duplicate){
            createAccountPresenter.prepareFailView("Duplicate Account found");
        }
        else {
            userDataAccessObject.addAccount(createAccountInputData.getTitle(), createAccountInputData.getUsername(),
                    createAccountInputData.getPassword(), createAccountInputData.getSecretKey(), createAccountInputData.getURL(),
                    createAccountInputData.getIconURL(), createAccountInputData.getDate(), createAccountInputData.getNotes());
            CreateAccountOutputData createAccountOutputData = new CreateAccountOutputData(true, userDataAccessObject.getAccounts());
            createAccountPresenter.prepareSuccessView(createAccountOutputData);
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
        createAccountPresenter.switchView();
    }
}
