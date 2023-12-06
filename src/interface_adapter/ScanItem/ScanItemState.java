package interface_adapter.ScanItem;

import entity.AccountInfo;
import interface_adapter.Authentication.AuthenticationState;
import interface_adapter.Dashboard.DashboardState;

import java.util.List;

public class ScanItemState {

    private String results = "";
    private String url = "";
    private String error = null;

    /**
     * Constructor method for the Scan Item view's state
     * @param copy A copy of the Scan Item's state
     */
    public ScanItemState(ScanItemState copy) {
        this.results = copy.results;
        this.url = copy.url;
        this.error = copy.error;
    }

    /**
     * Alternative constructor method for the Scan Item state for no copy
     * which keeps attributes initialized as null/empty strings
     */
    public ScanItemState() {
    }

    /**
     * Getter method for the results
     * @return The username
     */
    public String getResults() { return this.results; }

    /**
     * Getter method for the url
     * @return The url
     */
    public String getUrl() { return url; }

    /**
     * Getter method for the error
     * @return The error that will be displayed
     */
    public String getError() { return error; }

    /**
     * Setter method for the results
     * @param results The results of the file scan to be set
     */
    public void setResults(String results) {
        this.results = results;
    }

    /**
     * Setter method for the url
     * @param url The url that is to be scanned
     */
    public void setUrl(String url) { this.url = url; }

    /**
     * Setter method for the error
     * @param error The error that is to be displayed
     */
    public void setError(String error) {
        this.error = error; }
}
