package use_case.ScanItem;

import use_case.Dashboard.DashboardInputData;

import java.io.IOException;

public interface ScanItemInputBoundary {
    /**
     * Method which contains the logic for scanning a file in the Scan Item use case
     * @param scanItemInputData The input data for the use case interactor
     */
    void scanFile(ScanItemInputData scanItemInputData);

    /**
     * Method which contains the logic for scanning a url in the Scan Item use case
     * @param scanItemInputData The input data for the use case interactor
     */
    void scanUrl(ScanItemInputData scanItemInputData) throws IOException, InterruptedException;

    /**
     * Method for switching to Dashboard view
     */
    void switchView();
}
