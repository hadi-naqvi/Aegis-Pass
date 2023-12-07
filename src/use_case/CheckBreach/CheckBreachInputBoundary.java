package use_case.CheckBreach;

import java.io.IOException;

public interface CheckBreachInputBoundary {

    /**
     * Method which contains the logic for checking if an email has been breached for the Check Breach use case
     * @param checkBreachInputData the input data for the use case interactor
     */
    void checkEmail(CheckBreachInputData checkBreachInputData) throws IOException, InterruptedException;

    /**
     * Method which contains the logic for checking if a password has been breached for the Check Breach use case
     * @param checkBreachInputData the input data for the use case interactor
     */
    void checkPassword(CheckBreachInputData checkBreachInputData) throws IOException, InterruptedException;

    /**
     * Method for switching to Dashboard view
     */
    void switchView();

}
