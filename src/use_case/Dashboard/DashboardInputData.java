package use_case.Dashboard;

public class DashboardInputData {

    final private int userID;

    /**
     * Constructor method for the Dashboard use case's input data
     * @param userID The user ID for which the corresponding accounts need to be displayed
     */
    public DashboardInputData(int userID) {
        this.userID = userID;
    }

    /**
     * Getter method for the username being entered during dashboard display
     * @return The user ID being entered during dashboard display
     */
    public int getUserID() {
        return this.userID;
    }
}
