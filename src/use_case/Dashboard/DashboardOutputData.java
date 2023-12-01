package use_case.Dashboard;

public class DashboardOutputData {
    private final boolean useCaseFailed;

    /**
     * Consturctor method for the Dashboard output data
     * @param useCaseFailed Whether the use case failed or not (i.e. failed authentication)
     */
    public DashboardOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }
}
