package use_case.GenerateEmail;

import org.json.JSONException;
import use_case.Dashboard.DashboardOutputData;
import use_case.ScanItem.ScanItemDataAccessInterface;
import use_case.ScanItem.ScanItemOutputBoundary;

import java.io.IOException;

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
    public void execute(GenerateEmailInputData generateEmailInputData) throws IOException, InterruptedException {
        try {
            GenerateEmailOutputData generateEmailOutputData = new GenerateEmailOutputData(true,
                    generateEmailDataAccessObject.createEmail(generateEmailInputData.getAccountName(),
                            generateEmailInputData.getPassName()));
            generateEmailPresenter.prepareSuccessView(generateEmailOutputData);
        } catch (IOException | InterruptedException | JSONException e){
            generateEmailPresenter.prepareFailView("Uh oh, something went wrong. Try a different account name or password");
        }
    }


    /**
     * Method for switching to Dashboard view
     */
    public void switchView(){
        generateEmailPresenter.switchView();
    }
}
