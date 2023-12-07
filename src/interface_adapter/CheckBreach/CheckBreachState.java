package interface_adapter.CheckBreach;

public class CheckBreachState {

    private String results = "";
    private String email = "";
    private String password = "";
    private String error = null;

    /**
     * Constructor method for the Check Breach View's state
     * @param copy a copy of the Check Breach View's state
     */
    public CheckBreachState(CheckBreachState copy) {
        this.results = copy.results;
        this.email = copy.email;
        this.password = copy.password;
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
     * Getter method for the email
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for the email
     * @param email what to set email to
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for the password
     * @param password what to set password to
     */
    public void setPassword(String password) {
        this.password = password;
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
