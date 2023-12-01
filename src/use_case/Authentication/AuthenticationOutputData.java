package use_case.Authentication;

public class AuthenticationOutputData {
    private final boolean useCaseFailed;
    private final int currentUserID;

    /**
     * Consturctor method for the authentication output data
     * @param useCaseFailed Whether the use case failed or not (i.e. failed authentication)
     */
    public AuthenticationOutputData(boolean useCaseFailed, int currentUserID) {
        this.useCaseFailed = useCaseFailed;
        this.currentUserID = currentUserID;
    }

    /**
     * Getter method for the current user's id
     * @return The current user's id
     */
    public int getUserID() {
        return this.currentUserID;
    }
}