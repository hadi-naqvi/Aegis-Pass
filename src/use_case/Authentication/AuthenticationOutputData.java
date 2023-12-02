package use_case.Authentication;

public class AuthenticationOutputData {
    private final boolean useCaseFailed;
    private final int currentUserID;
    private final String currentUserSalt;

    /**
     * Consturctor method for the authentication output data
     * @param useCaseFailed Whether the use case failed or not (i.e. failed authentication)
     */
    public AuthenticationOutputData(boolean useCaseFailed, int currentUserID, String currentUserSalt) {
        this.useCaseFailed = useCaseFailed;
        this.currentUserID = currentUserID;
        this.currentUserSalt = currentUserSalt;
    }

    /**
     * Getter method for the current user's id
     * @return The current user's id
     */
    public int getUserID() {
        return this.currentUserID;
    }

    /**
     * Getter method for the current user's kdf salt
     * @return The current user's kdf salt
     */
    public String getUserSalt() {
        return this.currentUserSalt;
    }
}