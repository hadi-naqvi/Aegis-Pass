package use_case.GenerateEmail;

import data_access.InMemoryGenEmailDataAccessObject;
import org.junit.Test;
import use_case.ScanItem.ScanItemOutputData;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GenerateEmailInteractorTest {

    @Test
    public void successTest() throws IOException, InterruptedException {
        GenerateEmailDataAccessInterface emailRepository = new InMemoryGenEmailDataAccessObject();

        GenerateEmailOutputBoundary presenter = new GenerateEmailOutputBoundary() {
            @Override
            public void prepareSuccessView(GenerateEmailOutputData generateEmailOutputData) {
                assertEquals(generateEmailOutputData.getResult(), "alex@domain.com");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchView() {
                fail("Switch views is unexpected.");
            }
        };

        GenerateEmailInputBoundary interactor = new GenerateEmailInteractor(emailRepository, presenter);
        GenerateEmailInputData inputData = new GenerateEmailInputData("alex", "nullPass");
        interactor.execute(inputData);
    }

    @Test
    public void failureTest() throws IOException, InterruptedException {
        GenerateEmailDataAccessInterface emailRepository = new InMemoryGenEmailDataAccessObject();

        GenerateEmailOutputBoundary presenter = new GenerateEmailOutputBoundary() {
            @Override
            public void prepareSuccessView(GenerateEmailOutputData generateEmailOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {

            }

            @Override
            public void switchView() {
                fail("Switch views is unexpected.");
            }
        };

        GenerateEmailInputBoundary interactor = new GenerateEmailInteractor(emailRepository, presenter);
        GenerateEmailInputData inputData = new GenerateEmailInputData("", "nullPass");
        interactor.execute(inputData);
    }

}
