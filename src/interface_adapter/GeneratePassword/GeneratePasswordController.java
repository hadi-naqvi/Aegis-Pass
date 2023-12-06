package interface_adapter.GeneratePassword;

import use_case.Dashboard.DashboardInputBoundary;
import use_case.Dashboard.DashboardInputData;
import use_case.GeneratePassword.GeneratePasswordInputBoundary;
import use_case.GeneratePassword.GeneratePasswordInputData;

public class GeneratePasswordController {
    public final GeneratePasswordInputBoundary generatePasswordInteractor;

    /**
     * Constructor method for the controller for the generate password use case
     * @param generatePasswordInteractor The use case interactor object for the generate password use case
     */
    public GeneratePasswordController(GeneratePasswordInputBoundary generatePasswordInteractor) {
        this.generatePasswordInteractor = generatePasswordInteractor;
    }

    /**
     * Method which is executed/triggered when the user generates a new password
     */
    public void execute(int passwordQuality, int passwordLength, boolean lowerAlpha,
                        boolean upperAlpha, boolean numericalChars, boolean extendedAscii,
                        boolean punctuationOne, boolean punctuationTwo, boolean punctuationThree,
                        boolean punctuationFour, boolean punctuationFive, String alsoIncludeFrom,
                        String excludeFrom) {
        GeneratePasswordInputData generatePasswordInputData = new GeneratePasswordInputData(passwordQuality, passwordLength,
                lowerAlpha, upperAlpha, numericalChars, extendedAscii, punctuationOne, punctuationTwo, punctuationThree,
                punctuationFour, punctuationFive, alsoIncludeFrom, excludeFrom);
        generatePasswordInteractor.execute(generatePasswordInputData);
    }
}
