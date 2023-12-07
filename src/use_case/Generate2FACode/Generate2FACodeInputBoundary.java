package use_case.Generate2FACode;

import use_case.CreateAccount.CreateAccountInputData;

public interface Generate2FACodeInputBoundary {
    /**
     * Method which contains the logic for the Generate2FACode use case (interactor) which is triggered to complete use case
     * @param generate2FACodeInputData The input data for the use case interactor
     */
    void execute(Generate2FACodeInputData generate2FACodeInputData);
}
