package use_case.SetupAuth;

public class SetupAuthOutputData {
    private boolean useCaseFailed;

    /**
     * Constructor method for the output data in the SetupAuth use case
     * @param useCaseFailed Whether the use-case failed (i.e. if the username already exists or the passwords do not match it fails)
     */
    public SetupAuthOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }
}
