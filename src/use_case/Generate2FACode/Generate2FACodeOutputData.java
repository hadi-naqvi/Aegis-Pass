package use_case.Generate2FACode;

import entity.AccountInfo;

import java.util.List;

public class Generate2FACodeOutputData {
    private final boolean useCaseFailed;
    private final String code;

    /**
     * Constructor method for the Generate2FACode output data
     * @param useCaseFailed Whether the use case failed or not
     */
    public Generate2FACodeOutputData(boolean useCaseFailed, String code) {
        this.useCaseFailed = useCaseFailed;
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }
}
