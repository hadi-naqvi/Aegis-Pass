package interface_adapter.Generate2FACode;

import interface_adapter.ViewManagerModel;
import use_case.Generate2FACode.Generate2FACodeOutputBoundary;
import use_case.Generate2FACode.Generate2FACodeOutputData;

public class Generate2FACodePresenter implements Generate2FACodeOutputBoundary {

    private final Generate2FACodeViewModel generate2FACodeViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * constructor for the presenter
     * @param generate2FACodeViewModel
     * @param viewManagerModel
     */
    public Generate2FACodePresenter(Generate2FACodeViewModel generate2FACodeViewModel, ViewManagerModel viewManagerModel) {
        this.generate2FACodeViewModel = generate2FACodeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(Generate2FACodeOutputData generate2FACodeOutputData) {
        Generate2FACodeState generate2FACodeState = generate2FACodeViewModel.getState();
        generate2FACodeState.setFaCode(generate2FACodeOutputData.getCode());
        generate2FACodeViewModel.setState(generate2FACodeState);
    }

    /**
     * empty method
     * @param error
     */
    @Override
    public void prepareFailView(String error) {
    }
}
