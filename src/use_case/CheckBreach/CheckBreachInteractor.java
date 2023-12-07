package use_case.CheckBreach;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class CheckBreachInteractor implements CheckBreachInputBoundary {
    
    private CheckBreachDataAccessInterface checkBreachDataAccessObject;
    private CheckBreachOutputBoundary checkBreachPresenter;

    /**
     * Constructor for the Interactor of the Check Breach use case
     * @param dataAccessObject the input data for the Check Breach use case
     * @param presenter the presenter for the Check Breach use case
     */
    public CheckBreachInteractor(CheckBreachDataAccessInterface dataAccessObject, CheckBreachOutputBoundary presenter) {
        this.checkBreachDataAccessObject = dataAccessObject;
        this.checkBreachPresenter = presenter;
    }
    
    /**
     * Method which contains the logic for checking if an email has been breached for the Check Breach use case
     *
     * @param checkBreachInputData the input data for the use case interactor
     */
    @Override
    public void checkEmail(CheckBreachInputData checkBreachInputData) throws IOException, InterruptedException {
        try {
            // perform business logic, interact with data access
            if (Objects.equals(checkBreachInputData.getEmail(), "")) {
                checkBreachPresenter.prepareFailView("Please input an email");
            } else {
                CheckBreachOutputData outputData = new CheckBreachOutputData(false,
                        checkBreachDataAccessObject.checkEmail(checkBreachInputData.getEmail()));
                checkBreachPresenter.prepareSuccessView(outputData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method which contains the logic for checking if a password has been breached for the Check Breach use case
     *
     * @param checkBreachInputData the input data for the use case interactor
     */
    @Override
    public void checkPassword(CheckBreachInputData checkBreachInputData) throws IOException, InterruptedException {
        try {
            // perform business logic, interact with data access
            if (Objects.equals(checkBreachInputData.getPassword(), "")) {
                checkBreachPresenter.prepareFailView("Please input a password");
            } else {
                CheckBreachOutputData outputData = new CheckBreachOutputData(false,
                        checkBreachDataAccessObject.checkPassword(checkBreachInputData.getPassword()));
                checkBreachPresenter.prepareSuccessView(outputData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that tells the presenter to switch to the Dashboard View
     */
    @Override
    public void switchView() {
        checkBreachPresenter.switchView();
    }
    
}
