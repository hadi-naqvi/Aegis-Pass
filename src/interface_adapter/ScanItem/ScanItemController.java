package interface_adapter.ScanItem;

import use_case.ScanItem.ScanItemInputBoundary;
import use_case.ScanItem.ScanItemInputData;

import java.io.IOException;

public class ScanItemController {
    public final ScanItemInputBoundary scanItemInteractor;

    /**
     * Constructor method for the controller for the authentication use case
     * @param scanItemInteractor The use case interactor object for the scanItem use case
     */
    public ScanItemController(ScanItemInputBoundary scanItemInteractor) {
        this.scanItemInteractor = scanItemInteractor;
    }

    /**
     * Method which is executed when the user scans a file
     */
    public void scanFile(String filePath) {
        ScanItemInputData scanItemInputData = new ScanItemInputData(filePath, "");
        scanItemInteractor.scanFile(scanItemInputData);
    }

    /**
     * Method which is executed when the user scans a URL
     */
    public void scanUrl(String url) throws IOException, InterruptedException {
        ScanItemInputData scanItemInputData = new ScanItemInputData("", url);
        scanItemInteractor.scanUrl(scanItemInputData);
    }

    /**
     * Method for switching to Dashboard view
     */
    public void switchView(){
        scanItemInteractor.switchView();
    }
}
