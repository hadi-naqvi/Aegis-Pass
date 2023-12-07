package app;

import interface_adapter.Dashboard.DashboardViewModel;
import interface_adapter.Generate2FACode.Generate2FACodeController;
import interface_adapter.Generate2FACode.Generate2FACodePresenter;
import interface_adapter.Generate2FACode.Generate2FACodeState;
import interface_adapter.Generate2FACode.Generate2FACodeViewModel;
import interface_adapter.GeneratePassword.GeneratePasswordController;
import interface_adapter.GeneratePassword.GeneratePasswordPresenter;
import interface_adapter.GeneratePassword.GeneratePasswordViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Generate2FACode.Generate2FACodeInputBoundary;
import use_case.Generate2FACode.Generate2FACodeInteractor;
import use_case.Generate2FACode.Generate2FACodeOutputBoundary;
import use_case.GeneratePassword.GeneratePasswordInputBoundary;
import use_case.GeneratePassword.GeneratePasswordInteractor;
import use_case.GeneratePassword.GeneratePasswordOutputBoundary;

public class Generate2FACodeUseCaseFactory {
    public static Generate2FACodeController createGenerate2FACodeUseCase(ViewManagerModel viewManagerModel,
                                                                         Generate2FACodeViewModel generate2FACodeViewModel) {
        Generate2FACodeOutputBoundary generate2FACodePresenter = new Generate2FACodePresenter(generate2FACodeViewModel, viewManagerModel);
        Generate2FACodeInputBoundary generate2FACodeInteractor = new Generate2FACodeInteractor(generate2FACodePresenter);
        return new Generate2FACodeController(generate2FACodeInteractor);
    }
}
