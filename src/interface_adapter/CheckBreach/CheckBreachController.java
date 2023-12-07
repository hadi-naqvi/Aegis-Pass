package interface_adapter.CheckBreach;

import use_case.CheckBreach.CheckBreachInputBoundary;
import use_case.CheckBreach.CheckBreachInputData;

import java.io.IOException;

public class CheckBreachController {

    public final CheckBreachInputBoundary checkBreachInteractor;

    /**
     * Constructor method for the controller for the Check Breach use case
     * @param checkBreachInteractor the use case interactor object for the Check Breach use case
     */
    public CheckBreachController(CheckBreachInputBoundary checkBreachInteractor) {
        this.checkBreachInteractor = checkBreachInteractor;
    }

    /**
     * Method which is executed when the user gives a email to be checked for breaches
     * @param email the email to be checked for breaches
     */
    public void checkEmail(String email) throws IOException, InterruptedException {
        CheckBreachInputData inputData = new CheckBreachInputData(email, "");
        checkBreachInteractor.checkEmail(inputData);
    }

    /**
     * Method which is executed when the user gives a password to be checked for breaches
     * @param password the password to be checked for breaches
     */
    public void checkPassword(String password) throws IOException, InterruptedException {
        CheckBreachInputData inputData = new CheckBreachInputData("", password);
        checkBreachInteractor.checkPassword(inputData);
    }

    /**
     * Method for switching to dashboard view
     */
    public void switchView() {
        checkBreachInteractor.switchView();
    }

}
