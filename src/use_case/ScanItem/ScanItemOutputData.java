package use_case.ScanItem;

import entity.AccountInfo;

import java.util.List;

public class ScanItemOutputData {
    private final boolean useCaseFailed;
    private String results;

    /**
     * Constructor method for the Dashboard output data
     */
    public ScanItemOutputData(boolean useCaseFailed, String results) {
        this.useCaseFailed = useCaseFailed;
        this.results = results;
    }

    public String getResults() {
        return results;
    }

}
