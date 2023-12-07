package use_case.GenerateEmail;

public class GenerateEmailInputData {

    final private String accountName;
    final private String passName;

    /**
     * Constructor method for the Generate Email use case's input data
     */
    public GenerateEmailInputData(String accountName, String passName) {
        this.accountName = accountName;
        this.passName = passName;
    }

    /**
     * Getter method for the account name that is entered
     * @return The account name being entered
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Getter method for the password name that is entered
     * @return The password name being entered
     */
    public String getPassName() { return passName; }
}
