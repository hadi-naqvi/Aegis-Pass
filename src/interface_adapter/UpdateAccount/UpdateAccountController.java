package interface_adapter.UpdateAccount;

import use_case.UpdateAccount.UpdateAccountInputBoundary;
import use_case.UpdateAccount.UpdateAccountInputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateAccountController {
    public final UpdateAccountInputBoundary updateAccountInteractor;

    /**
     * Constructor method for the controller for the updateAccount use case
     * @param updateAccountInteractor The use case interactor object for the updateAccount use case
     */
    public UpdateAccountController(UpdateAccountInputBoundary updateAccountInteractor) {
        this.updateAccountInteractor = updateAccountInteractor;
    }

    /**
     * Method which is executed/triggered when the user enters the updateAccount view
     */
    public void execute(int accountIndex, String title, String username, String password, String secretKey, String url,
                        String iconURL, LocalDateTime date, String notes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        UpdateAccountInputData updateAccountInputData = new UpdateAccountInputData(accountIndex, title, username, password,
                secretKey, url, iconURL, formatter.format(date), notes);
        updateAccountInteractor.execute(updateAccountInputData);
    }

    public void switchView(){
        updateAccountInteractor.switchView();
    }
}
