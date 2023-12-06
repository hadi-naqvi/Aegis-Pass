package use_case.CheckBreach;

public interface CheckBreachOutputBoundary {

    /**
     * Method which updates the view manager model to display the breach info for an email or password
     * @param checkBreachOutputData the output data for the Check Breach use case (the breach info)
     */
    void prepareSuccessView(CheckBreachOutputData checkBreachOutputData);

    /**
     * Method which updates the view manager model to display the failed message for the use case
     * @param error the error message which explains why Check Breach failed
     */
    void prepareFailView(String error);

    /**
     * Method for switching to Dashboard view
     */
    void switchView();

}
