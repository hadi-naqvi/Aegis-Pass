package interface_adapter.LogOut;

import use_case.LogOut.LogOutInputBoundary;
import use_case.LogOut.LogOutInputData;

public class LogOutController {
    public final LogOutInputBoundary logOutInteractor;

    /**
     * Constructor method for the controller for the logout use case
     * @param logOutInteractor The use case interactor object for the logOutInteractor use case
     */
    public LogOutController(LogOutInputBoundary logOutInteractor) {
        this.logOutInteractor = logOutInteractor;
    }

    /**
     * Method which is executed/triggered when the user clicks the log out button
     */
    public void execute() {
        LogOutInputData logOutInputData = new LogOutInputData();
        logOutInteractor.execute(logOutInputData);
    }
}