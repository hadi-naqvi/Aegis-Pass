package use_case.CheckBreach;

import data_access.InMemoryBreachDataAccessObject;
import org.junit.Test;
import use_case.SetupAuth.SetupAuthOutputBoundary;
import use_case.SetupAuth.SetupAuthOutputData;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CheckBreachInteractorTest {

    @Test
    public void successEmailTest() throws IOException, InterruptedException {
        CheckBreachDataAccessInterface breachRepository = new InMemoryBreachDataAccessObject();

        CheckBreachOutputBoundary presenter = new CheckBreachOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckBreachOutputData checkBreachOutputData) {
                assertEquals(checkBreachOutputData.getResults(), "Your email was found in breaches from: Deezer, Canva");
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

        CheckBreachInputBoundary interactor = new CheckBreachInteractor(breachRepository, presenter);
        CheckBreachInputData inputData = new CheckBreachInputData("a", "");
        interactor.checkEmail(inputData);
    }

    @Test
    public void failEmptyEmailTest() throws IOException, InterruptedException {
        CheckBreachDataAccessInterface breachRepository = new InMemoryBreachDataAccessObject();

        CheckBreachOutputBoundary presenter = new CheckBreachOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckBreachOutputData checkBreachOutputData) {
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

        CheckBreachInputBoundary interactor = new CheckBreachInteractor(breachRepository, presenter);
        CheckBreachInputData inputData = new CheckBreachInputData("", "");
        interactor.checkEmail(inputData);
    }

    @Test
    public void successPasswordTest() throws IOException, InterruptedException {
        CheckBreachDataAccessInterface breachRepository = new InMemoryBreachDataAccessObject();

        CheckBreachOutputBoundary presenter = new CheckBreachOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckBreachOutputData checkBreachOutputData) {
                assertEquals(checkBreachOutputData.getResults(), "Your password has been breached x times");
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

        CheckBreachInputBoundary interactor = new CheckBreachInteractor(breachRepository, presenter);
        CheckBreachInputData inputData = new CheckBreachInputData("", "x");
        interactor.checkPassword(inputData);
    }

    @Test
    public void failEmptyPasswordTest() throws IOException, InterruptedException {
        CheckBreachDataAccessInterface breachRepository = new InMemoryBreachDataAccessObject();

        CheckBreachOutputBoundary presenter = new CheckBreachOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckBreachOutputData checkBreachOutputData) {
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

        CheckBreachInputBoundary interactor = new CheckBreachInteractor(breachRepository, presenter);
        CheckBreachInputData inputData = new CheckBreachInputData("", "");
        interactor.checkPassword(inputData);
    }

}
