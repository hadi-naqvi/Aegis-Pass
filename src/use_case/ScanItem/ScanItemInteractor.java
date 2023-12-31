package use_case.ScanItem;

import java.io.IOException;
import java.net.URISyntaxException;

public class ScanItemInteractor implements ScanItemInputBoundary {

    private ScanItemDataAccessInterface scanItemDataAccessObject;
    private ScanItemOutputBoundary scanItemPresenter;

    public ScanItemInteractor(ScanItemDataAccessInterface dataAccess, ScanItemOutputBoundary scanItemOutputBoundary) {
        this.scanItemDataAccessObject = dataAccess;
        this.scanItemPresenter = scanItemOutputBoundary;
    }

    /**
     * Method which contains the logic for scanning a file in the Scan Item use case
     * @param scanItemInputData The input data for the use case interactor
     */
    @Override
    public void scanFile(ScanItemInputData scanItemInputData) {
        try {
            // Perform business logic, interact with data access, etc.
            ScanItemOutputData scanItemOutputData = new ScanItemOutputData(true,
                    scanItemDataAccessObject.scanFile(scanItemInputData.getFilePath()));
            scanItemPresenter.prepareSuccessView(scanItemOutputData);
        } catch (IOException e) {
            // Handle IOException (e.g., log or present an error to the user)
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method which contains the logic for scanning a url in the Scan Item use case
     * @param scanItemInputData The input data for the use case interactor
     */
    @Override
    public void scanUrl(ScanItemInputData scanItemInputData) throws IOException, InterruptedException {
        if (!(scanItemDataAccessObject.isValidUrl(scanItemInputData.getUrl()))) {
            scanItemPresenter.prepareFailView("Invalid URL");
        }
        else {
            try {
                // Perform business logic, interact with data access, etc.
                ScanItemOutputData scanItemOutputData = new ScanItemOutputData(true, scanItemDataAccessObject.scanUrl(scanItemInputData.getUrl()));
                scanItemPresenter.prepareSuccessView(scanItemOutputData);
            } catch (IOException e) {
                // Handle IOException (e.g., log or present an error to the user)
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method for switching to Dashboard view
     */
    public void switchView(){
        scanItemPresenter.switchView();
    }
}
