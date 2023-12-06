package use_case.UpdateAccount;

import entity.AccountInfo;

import java.util.List;

public class UpdateAccountOutputData {
    private final boolean useCaseFailed;
    private List<AccountInfo> accounts;

    /**
     * Constructor method for the UpdateAccount output data
     * @param useCaseFailed Whether the use case failed or not (i.e. failed updating account)
     */
    public UpdateAccountOutputData(boolean useCaseFailed, List<AccountInfo> accounts) {
        this.useCaseFailed = useCaseFailed;
        this.accounts = accounts;
    }

    public List<AccountInfo> getAccounts(){
        return this.accounts;
    }
}
