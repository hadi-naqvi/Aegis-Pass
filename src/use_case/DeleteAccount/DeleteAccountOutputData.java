package use_case.DeleteAccount;

import entity.AccountInfo;

import java.util.List;

public class DeleteAccountOutputData {

    private final boolean useCaseFailed;
    private List<AccountInfo> accounts;

    /**
     * Constructor method for the Delete Account output data
     * @param useCaseFailed Whether the use case failed or not (i.e. failed deleting account)
     */
    public DeleteAccountOutputData(boolean useCaseFailed, List<AccountInfo> accounts) {
        this.useCaseFailed = useCaseFailed;
        this.accounts = accounts;
    }

    public List<AccountInfo> getAccounts(){
        return this.accounts;
    }

}
