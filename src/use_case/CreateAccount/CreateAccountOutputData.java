package use_case.CreateAccount;


import entity.AccountInfo;

import java.util.List;

public class CreateAccountOutputData {
    private final boolean useCaseFailed;
    private List<AccountInfo> accounts;

    /**
     * Constructor method for the CreateAccount output data
     * @param useCaseFailed Whether the use case failed or not (i.e. failed creating account)
     */
    public CreateAccountOutputData(boolean useCaseFailed, List<AccountInfo> accounts) {
        this.useCaseFailed = useCaseFailed;
        this.accounts = accounts;
    }

    public List<AccountInfo> getAccounts(){
        return this.accounts;
    }
}
