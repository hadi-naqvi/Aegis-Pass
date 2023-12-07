package use_case.GeneratePassword;

import use_case.Dashboard.DashboardInputData;

public interface GeneratePasswordInputBoundary {
    /**
     * Method which contains the logic for the Generate Password use case (interactor) which is triggered to complete use case
     * @param generatePasswordInputData The input data for the use case interactor
     */
    void execute(GeneratePasswordInputData generatePasswordInputData);
}
