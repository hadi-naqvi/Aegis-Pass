package use_case.LogOut;

import use_case.Dashboard.DashboardInputData;

public interface LogOutInputBoundary {
    /**
     * Method which contains the logic for the LogOut use case (interactor) which is triggered to complete use case
     * @param logOutInputData The input data for the use case interactor
     */
    void execute(LogOutInputData logOutInputData);
}
