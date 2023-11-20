package use_case.Authentication;

public class AuthenticationOutputData {
    private boolean useCaseFailed;

    /**
     * Consturctor method for the authentication output data
     * @param useCaseFailed Whether the use case failed or not (i.e. failed authentication)
     */
    public AuthenticationOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }
}