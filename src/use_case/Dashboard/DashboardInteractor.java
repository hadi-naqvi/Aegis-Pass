package use_case.Dashboard;

public class DashboardInteractor implements DashboardInputBoundary {
    final private DashboardDataAccessInterface userDataAccessObject;
    final private DashboardOutputBoundary dashboardPresenter;

    /**
     * Constructor method for the use case interactor for the Dashboard use case
     * @param userDataAccessObject The data access object which provides access to the stored data required
     * @param dashboardPresenter The presenter for the Dashboard use case
     */
    public DashboardInteractor(DashboardDataAccessInterface userDataAccessObject,
                                    DashboardOutputBoundary dashboardPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.dashboardPresenter = dashboardPresenter;
    }

    /**
     * Method which contains the logic for the dashboard use case (interactor) which is triggered to complete use case
     * @param dashboardInputData input data for the dashboard use case
     */
    @Override
    public void execute(DashboardInputData dashboardInputData) {
        userDataAccessObject.getAccounts();
        DashboardOutputData dashboardOutputData = new DashboardOutputData(true);
        dashboardPresenter.prepareSuccessView(dashboardOutputData);
        }
    }

