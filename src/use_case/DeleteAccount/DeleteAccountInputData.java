package use_case.DeleteAccount;

public class DeleteAccountInputData {

    final private String title;
    final private String username;

    /**
     * Constructor for the Delete Account use case's input data
     * @param title of the account to be deleted
     * @param username of the account to be deleted
     */
    public DeleteAccountInputData(String title, String username) {
        this.title = title;
        this.username = username;
    }

    /**
     * Getter method for the title of the account to be deleted
     * @return The username of the account.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter method for the username of the account to be deleted
     * @return The username of the account.
     */
    public String getUsername() {
        return username;
    }
}
