package use_case.GenerateEmail;

public interface GenerateEmailInputBoundary {
    /**
     * Method which contains the logic for generating an ephemeral email for the generate email use case
     * @param generateEmailInputData The input data for the use case interactor
     */
    void execute(GenerateEmailInputData generateEmailInputData);
}
