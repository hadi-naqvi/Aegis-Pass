package use_case.CheckPassQuality;

public interface CheckPassQualityInputBoundary {
    /**
     * Method which contains the logic for the check password quality use case (interactor) which is triggered to complete use case
     * @param checkPassQualityInputData The input data for the use case interactor
     */
    void execute(CheckPassQualityInputData checkPassQualityInputData);
}
