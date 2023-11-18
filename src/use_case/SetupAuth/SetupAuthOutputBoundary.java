package use_case.SetupAuth;

public interface SetupAuthOutputBoundary {
    void prepareSuccessView(SetupAuthOutputData setupAuthOutputData);

    void prepareFailView(String error);
}
