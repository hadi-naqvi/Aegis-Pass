package use_case.LogOut;

import use_case.Dashboard.DashboardDataAccessInterface;
import use_case.Dashboard.DashboardOutputBoundary;

public class LogOutInteractor implements LogOutInputBoundary {
    final private LogOutDataAccessInterface userDataAccessObject;
    final private LogOutOutputBoundary logOutPresenter;

    /**
     * Constructor method for the use case interactor for the LogOut use case
     * @param userDataAccessObject The data access object which provides access to the stored data required
     * @param logOutPresenter The presenter for the Dashboard use case
     */
    public LogOutInteractor(LogOutDataAccessInterface userDataAccessObject,
                               LogOutOutputBoundary logOutPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.logOutPresenter = logOutPresenter;
    }

    /**
     * Method which contains the logic for the LogOut use case (interactor) which is triggered to complete use case
     *
     * @param logOutInputData The input data for the use case interactor
     */
    @Override
    public void execute(LogOutInputData logOutInputData) {
        this.userDataAccessObject.setCurrentUserID(-1);
        this.userDataAccessObject.setEncryptionKey("");
        this.logOutPresenter.prepareSuccessView(new LogOutOutputData(false));
    }
}
