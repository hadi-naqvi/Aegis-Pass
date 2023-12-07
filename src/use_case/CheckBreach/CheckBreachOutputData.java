package use_case.CheckBreach;

public class CheckBreachOutputData {

    private final boolean useCaseFailed;
    private String results;

    /**
     * Constructor method for the Output Data of the Check Breach use case
     * @param useCaseFailed boolean if the use cas faild or not (if breach info was obtained or not)
     * @param results string of results of the breach check
     */
    public CheckBreachOutputData(boolean useCaseFailed, String results) {
        this.useCaseFailed = useCaseFailed;
        this.results = results;
    }

    /**
     * Getter method for the results of the breach check
     * @return the results of the breach check
     */
    public String getResults() {
        return results;
    }
}
