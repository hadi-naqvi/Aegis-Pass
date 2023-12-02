package use_case.Dashboard;

import entity.AccountInfo;

import java.util.List;

public class DashboardOutputData {
    private final boolean useCaseFailed;
    private List<AccountInfo> accounts;

    /**
     * Consturctor method for the Dashboard output data
     * @param useCaseFailed Whether the use case failed or not (i.e. failed authentication)
     */
    public DashboardOutputData(boolean useCaseFailed, List<AccountInfo> accounts) {
        this.useCaseFailed = useCaseFailed;
        this.accounts = accounts;
    }

    public List<AccountInfo> getAccounts(){
        return this.accounts;
    }
}
