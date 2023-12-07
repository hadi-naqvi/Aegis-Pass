package interface_adapter.Generate2FACode;


import use_case.Generate2FACode.Generate2FACodeInputBoundary;
import use_case.Generate2FACode.Generate2FACodeInputData;


public class Generate2FACodeController {
    public final Generate2FACodeInputBoundary generate2FACodeInteractor;

    /**
     * Constructor method for the controller for the generate2FACode use case
     * @param generate2FACodeInteractor The use case interactor object for the generate2FACode use case
     */
    public Generate2FACodeController(Generate2FACodeInputBoundary generate2FACodeInteractor) {
        this.generate2FACodeInteractor = generate2FACodeInteractor;
    }

    /**
     * Method which is executed/triggered when the user enters the CardView
     */
    public void execute(String secretKey) {
        Generate2FACodeInputData generate2FACodeInputData = new Generate2FACodeInputData(secretKey);
        generate2FACodeInteractor.execute(generate2FACodeInputData);
    }
}
