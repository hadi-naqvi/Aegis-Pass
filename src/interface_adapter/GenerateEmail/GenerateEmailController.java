package interface_adapter.GenerateEmail;

import use_case.GenerateEmail.GenerateEmailInputBoundary;
import use_case.GenerateEmail.GenerateEmailInputData;
import use_case.GenerateEmail.GenerateEmailInteractor;
import use_case.ScanItem.ScanItemInputBoundary;
import use_case.ScanItem.ScanItemInputData;

public class GenerateEmailController {
    public final GenerateEmailInputBoundary generateEmailInteractor;

    /**
     * Constructor method for the controller for the generate email use case
     * @param generateEmailInteractor The use case interactor object for the generateEmail use case
     */
    public GenerateEmailController(GenerateEmailInputBoundary generateEmailInteractor) {
        this.generateEmailInteractor = generateEmailInteractor;
    }

    /**
     * Method which is executed when the user generates an ephemeral email
     */
    public void execute(String accountName) {
        GenerateEmailInputData generateEmailInputData = new GenerateEmailInputData(accountName);
        generateEmailInteractor.execute(generateEmailInputData);
    }

}
