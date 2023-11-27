package use_case.Authentication;
public interface AuthenticationInputBoundary {
    /**
     * Method which contains the logic for the Authentication use case (interactor) which is triggered to complete use case
     * @param authenticationInputData The input data for the use case interactor
     */
    void execute(AuthenticationInputData authenticationInputData);
}