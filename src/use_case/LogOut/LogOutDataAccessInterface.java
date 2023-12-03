package use_case.LogOut;

public interface LogOutDataAccessInterface {
    /**
     * Setter method for the currently signed in user's id
     * @param userID the user id
     */
    void setCurrentUserID(int userID);

    /**
     * Setter method for the encryption key
     * @param key the encryption key
     */
    void setEncryptionKey(String key);
}
