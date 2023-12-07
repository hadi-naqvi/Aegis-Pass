package app;

import interface_adapter.CheckPassQuality.CheckPassQualityController;
import interface_adapter.CheckPassQuality.CheckPassQualityPresenter;
import interface_adapter.CheckPassQuality.CheckPassQualityViewModel;
import interface_adapter.GeneratePassword.GeneratePasswordViewModel;
import use_case.CheckPassQuality.CheckPassQualityInteractor;

public class CheckPassQualityUseCaseFactory {
    public static CheckPassQualityController createCheckPassQualityUseCase(GeneratePasswordViewModel generatePasswordViewModel,
                                                                           CheckPassQualityViewModel checkPassQualityViewModel) {
        CheckPassQualityPresenter checkPassQualityPresenter = new CheckPassQualityPresenter(checkPassQualityViewModel,
                generatePasswordViewModel);
        CheckPassQualityInteractor checkPassQualityInteractor = new CheckPassQualityInteractor(checkPassQualityPresenter);
        return new CheckPassQualityController(checkPassQualityInteractor);
    }
}
