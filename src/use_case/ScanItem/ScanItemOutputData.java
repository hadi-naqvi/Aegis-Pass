package use_case.ScanItem;

import entity.AccountInfo;

import java.util.List;

public class ScanItemOutputData {
    private final boolean useCaseFailed;
    private String results;

    /**
     * Constructor method for the ScanItem output data
     */
    public ScanItemOutputData(boolean useCaseFailed, String results) {
        this.useCaseFailed = useCaseFailed;
        this.results = results;
    }

    /**
     * Getter method for the results of the report
     */
    public String getResults() {
        return results;
    }

}
