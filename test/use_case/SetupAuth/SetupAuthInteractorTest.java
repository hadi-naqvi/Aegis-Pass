package use_case.SetupAuth;

import com.sun.net.httpserver.Authenticator;
import data_access.FileAuthDataAccessObject;
import data_access.InMemoryAuthDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SetupAuthInteractorTest {

    /**
     * Test method for SetupAuthInteractor where it successfully creates a new user.
     * This is NOT an integration test, we're using a "fake" Data Access Object that
     * only exists in-memory, instead of grabbing from our database.
     */
    @Test
    public void successTest() {
        SetupAuthDataAccessInterface authRepository = new InMemoryAuthDataAccessObject();

        SetupAuthOutputBoundary successPresenter = new SetupAuthOutputBoundary() {
            @Override
            public void prepareSuccessView(SetupAuthOutputData setupAuthOutputData) {
                assertTrue(authRepository.existsByName("Tester"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchViews() {
                fail("Switch views is unexpected.");
            }
        };

        UserFactory userFactory = new CommonUserFactory();
        SetupAuthInputBoundary interactor = new SetupAuthInteractor(userFactory, authRepository, successPresenter);
        SetupAuthInputData inputData = new SetupAuthInputData("Tester", "pass_word167", "pass_word167");
        interactor.execute(inputData);
    }

    /**
     * Test method for SetupAuthInteractor where it fails to create a new user due to
     * mismatched passwords. This is NOT an integration test, we're using a "fake"
     * Data Access Object that only exists in-memory, instead of grabbing from our database.
     */
    @Test
    public void failureMismatchTest() {
        SetupAuthDataAccessInterface authRepository = new InMemoryAuthDataAccessObject();

        SetupAuthOutputBoundary successPresenter = new SetupAuthOutputBoundary() {
            @Override
            public void prepareSuccessView(SetupAuthOutputData setupAuthOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {

            }

            @Override
            public void switchViews() {
                fail("Switch views is unexpected.");
            }
        };

        UserFactory userFactory = new CommonUserFactory();
        SetupAuthInputBoundary interactor = new SetupAuthInteractor(userFactory, authRepository, successPresenter);
        SetupAuthInputData inputData = new SetupAuthInputData("Tester", "pass_word167", "not_the_same");
        interactor.execute(inputData);
    }

}
