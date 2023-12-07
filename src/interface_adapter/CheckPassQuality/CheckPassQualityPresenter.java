package interface_adapter.CheckPassQuality;

import interface_adapter.GeneratePassword.GeneratePasswordViewModel;
import use_case.CheckPassQuality.CheckPassQualityOutputBoundary;
import use_case.CheckPassQuality.CheckPassQualityOutputData;

public class CheckPassQualityPresenter implements CheckPassQualityOutputBoundary {
    private final CheckPassQualityViewModel checkPassQualityViewModel;
    private final GeneratePasswordViewModel generatePasswordViewModel;

    public CheckPassQualityPresenter(CheckPassQualityViewModel checkPassQualityViewModel,
                                     GeneratePasswordViewModel generatePasswordViewModel) {
        this.checkPassQualityViewModel = checkPassQualityViewModel;
        this.generatePasswordViewModel = generatePasswordViewModel;
    }
    /**
     * Method which triggers the success view after the password quality check is complete
     *
     * @param checkPassQualityOutputData The output data of the use case
     */
    @Override
    public void prepareSuccessView(CheckPassQualityOutputData checkPassQualityOutputData) {
        CheckPassQualityState state = this.checkPassQualityViewModel.getState();
        state.setPasswordQuality(checkPassQualityOutputData.getPassQuality());
        this.checkPassQualityViewModel.setState(state);
    }

    /**
     * Method which triggers the fail view when checking password quality
     *
     * @param error The error message which explains why check password quality use case failed
     */
    @Override
    public void prepareFailView(String error) {
        CheckPassQualityState state = this.checkPassQualityViewModel.getState();
        state.setPasswordError(error);
        this.checkPassQualityViewModel.setState(state);

        // Currently this error popup is not displayed because it is not wanted, however can be added back later
        // generatePasswordViewModel.firePropertyChanged();
    }
}
