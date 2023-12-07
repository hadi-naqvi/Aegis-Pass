package use_case.GenerateEmail;

public class GenerateEmailOutputData {

    private final boolean useCaseFailed;

    private String result;

    /**
     * Constructor method for the Generate Email output data
     */
    public GenerateEmailOutputData(boolean useCaseFailed, String result){
        this.useCaseFailed = useCaseFailed;
        this.result = result;
    }

    /**
     * Getter method for the result of generating the email
     */
    public String getResult() {
        return result;
    }

}
