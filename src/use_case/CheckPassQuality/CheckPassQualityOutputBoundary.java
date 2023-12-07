package use_case.CheckPassQuality;

public interface CheckPassQualityOutputBoundary {
    /**
     * Method which triggers the success view after the password quality check is complete
     * @param checkPassQualityOutputData The output data of the use case
     */
    void prepareSuccessView(CheckPassQualityOutputData checkPassQualityOutputData);

    /**
     * Method which triggers the fail view when checking password quality
     * @param error The error message which explains why check password quality use case failed
     */
    void prepareFailView(String error);
}
