package interface_adapter.CheckBreach;

public class CheckBreachState {

    private String results = "";
    private String url = "";
    private String error = null;

    /**
     * Constructor method for the Check Breach View's state
     * @param copy a copy of the Check Breach View's state
     */
    public CheckBreachState(CheckBreachState copy) {
        this.results = copy.results;
        this.url = copy.url;
        this.error = copy.error;
    }

    /**
     * An alternative constructor to instantiate with null/empty attributes, for no copy
     */
    public CheckBreachState() {

    }

    /**
     * Getter method for the results
     * @return the results
     */
    public String getResults() {
        return results;
    }

    /**
     * Setter method for the results
     * @param results what to set results to
     */
    public void setResults(String results) {
        this.results = results;
    }

    /**
     * Getter method for the url
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for the url
     * @param url what to set url to
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter method for the error
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Setter method for the error
     * @param error what to set error to
     */
    public void setError(String error) {
        this.error = error;
    }
}
