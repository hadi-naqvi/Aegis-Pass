package use_case.ScanItem;

import use_case.Dashboard.DashboardInputData;

public interface ScanItemInputBoundary {
    /**
     * Method which contains the logic for the Scan Item use case (interactor) which is triggered to complete use case
     * @param scanItemInputData The input data for the use case interactor
     */
    void scanFile(ScanItemInputData scanItemInputData);

    void scanUrl(ScanItemInputData scanItemInputData);

    void switchView();
}
