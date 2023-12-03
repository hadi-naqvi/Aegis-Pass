package use_case.LogOut;

public class LogOutOutputData {
    private final boolean useCaseFailed;

    /**
     * Consturctor method for the LogOut output data
     * @param useCaseFailed Whether the use case failed or not (i.e. failed authentication)
     */
    public LogOutOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }
}
