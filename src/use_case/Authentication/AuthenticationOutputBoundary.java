package use_case.Authentication;

public interface AuthenticationOutputBoundary {
    void prepareSuccessView(AuthenticationOutputData authenticationOutputData);
    void prepareFailView(String error);
}