package use_case.GenerateEmail;

import use_case.Dashboard.DashboardOutputData;
import use_case.ScanItem.ScanItemDataAccessInterface;
import use_case.ScanItem.ScanItemOutputBoundary;

public class GenerateEmailInteractor implements GenerateEmailInputBoundary {

    private GenerateEmailDataAccessInterface generateEmailDataAccessObject;
    private GenerateEmailOutputBoundary generateEmailPresenter;

    public GenerateEmailInteractor(GenerateEmailDataAccessInterface dataAccess, GenerateEmailOutputBoundary generateEmailOutputBoundary) {
        this.generateEmailDataAccessObject = dataAccess;
        this.generateEmailPresenter = generateEmailOutputBoundary;
    }

    /**
     * Method which contains the logic for generating an ephemeral email for the generate email use case
     *
     * @param generateEmailInputData The input data for the use case interactor
     */
    @Override
    public void execute(GenerateEmailInputData generateEmailInputData) {
        GenerateEmailOutputData generateEmailOutputData = new GenerateEmailOutputData(true,
                generateEmailDataAccessObject.getAccounts());
        generateEmailPresenter.prepareSuccessView(generateEmailOutputData);
    }
}
