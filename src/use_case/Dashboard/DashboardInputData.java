package use_case.Dashboard;

public class DashboardInputData {

    final private String username;

    /**
     * Constructor method for the Dashboard use case's input data
     * @param username The username for which the corresponding accounts need to be displayed
     */
    public DashboardInputData(String username) {
        this.username = username;
    }

    /**
     * Getter method for the username being entered during dashboard display
     * @return The username being entered during dashboard display
     */
    public String getUsername() {
        return this.username;
    }
}
