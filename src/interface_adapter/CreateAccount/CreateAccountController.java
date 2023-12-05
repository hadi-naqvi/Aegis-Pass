package interface_adapter.CreateAccount;

import use_case.CreateAccount.CreateAccountInputBoundary;
import use_case.CreateAccount.CreateAccountInputData;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateAccountController {
    public final CreateAccountInputBoundary createAccountInteractor;

    /**
     * Constructor method for the controller for the createAccount use case
     * @param createAccountInteractor The use case interactor object for the createAccount use case
     */
    public CreateAccountController(CreateAccountInputBoundary createAccountInteractor) {
        this.createAccountInteractor = createAccountInteractor;
    }

    /**
     * Method which is executed/triggered when the user enters the createAccount view
     */
    public void execute(String title, String username, String password, String secretKey, String url,
                        String iconURL, LocalDateTime date, String notes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        CreateAccountInputData createAccountInputData = new CreateAccountInputData(title, username, password,
                secretKey, url, iconURL, formatter.format(date), notes);
        createAccountInteractor.execute(createAccountInputData);
    }

    public void switchView(){
        createAccountInteractor.switchView();
    }
}
