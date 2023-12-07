package interface_adapter.CheckPassQuality;

import use_case.CheckPassQuality.CheckPassQualityInputBoundary;
import use_case.CheckPassQuality.CheckPassQualityInputData;

public class CheckPassQualityController {
    private final CheckPassQualityInputBoundary checkPassQualityInteractor;

    /**
     * Constructor method for the check password quality use case's controller
     * @param checkPassQualityInteractor The interactor for the check password quality use case
     */
    public CheckPassQualityController(CheckPassQualityInputBoundary checkPassQualityInteractor) {
        this.checkPassQualityInteractor = checkPassQualityInteractor;
    }

    /**
     * Method which is executed/triggered when calculating the password quality of a password
     */
    public void execute(String password) {
        CheckPassQualityInputData checkPassQualityInputData = new CheckPassQualityInputData(password);
        checkPassQualityInteractor.execute(checkPassQualityInputData);
    }
}
