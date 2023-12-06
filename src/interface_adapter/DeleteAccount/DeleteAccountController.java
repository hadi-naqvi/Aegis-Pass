package interface_adapter.DeleteAccount;

import use_case.DeleteAccount.DeleteAccountInputBoundary;
import use_case.DeleteAccount.DeleteAccountInputData;
import use_case.DeleteAccount.DeleteAccountInteractor;

public class DeleteAccountController {

    public final DeleteAccountInputBoundary deleteAccountInteractor;

    /**
     * Constructor method for the controller for the Delete Account use case
     * @param deleteAccountInteractor The use case interactor object for the Delete Account use case
     */
    public DeleteAccountController(DeleteAccountInputBoundary deleteAccountInteractor) {
        this.deleteAccountInteractor = deleteAccountInteractor;
    }

    /**
     * Method which is executed/triggered when the delete button is clicked
     * @param title the title of the account to be deleted
     * @param username the username of the account to be deleted
     */
    public void execute(String title, String username) {
        DeleteAccountInputData inputData = new DeleteAccountInputData(title, username);
        deleteAccountInteractor.execute(inputData);
    }

    /**
     * Method that tells the interactor to switch views
     */
    public void switchView(){
        deleteAccountInteractor.switchView();
    }

}
