package use_case.GenerateEmail;

public class GenerateEmailInputData {

    final private String accountName;

    /**
     * Constructor method for the Generate Email use case's input data
     */
    public GenerateEmailInputData(String accountName) {
        this.accountName = accountName;
    }
}
