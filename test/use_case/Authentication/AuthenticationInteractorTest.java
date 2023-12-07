package use_case.Authentication;

import data_access.InMemoryAuthDataAccessObject;
import data_access.InMemoryDashDataAccessObject;
import org.junit.Test;
import use_case.Dashboard.DashboardDataAccessInterface;

import static org.junit.Assert.*;

public class AuthenticationInteractorTest {

    @Test
    public void successTest() {
        AuthenticationDataAccessInterface authRepository = new InMemoryAuthDataAccessObject("alex", "macri");
        DashboardDataAccessInterface dashboardRepository = new InMemoryDashDataAccessObject();

        AuthenticationOutputBoundary presenter = new AuthenticationOutputBoundary() {
            @Override
            public void prepareSuccessView(AuthenticationOutputData authenticationOutputData) {
                assertTrue(authRepository.validate("alex", "macri"));
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

        AuthenticationInputBoundary interactor = new AuthenticationInteractor(authRepository, dashboardRepository, presenter);
        AuthenticationInputData inputData = new AuthenticationInputData("alex", "macri");
        interactor.execute(inputData);
    }

    @Test
    public void failureMismatchTest() {
        AuthenticationDataAccessInterface authRepository = new InMemoryAuthDataAccessObject("alex", "macro");
        DashboardDataAccessInterface dashboardRepository = new InMemoryDashDataAccessObject();

        AuthenticationOutputBoundary presenter = new AuthenticationOutputBoundary() {
            @Override
            public void prepareSuccessView(AuthenticationOutputData authenticationOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertFalse(authRepository.validate("alex", "macri"));
            }

            @Override
            public void switchViews() {
                fail("Switch views is unexpected.");
            }
        };

        AuthenticationInputBoundary interactor = new AuthenticationInteractor(authRepository, dashboardRepository, presenter);
        AuthenticationInputData inputData = new AuthenticationInputData("alex", "macri");
        interactor.execute(inputData);
    }

    @Test
    public void failureDoesNotExistTest() {
        AuthenticationDataAccessInterface authRepository = new InMemoryAuthDataAccessObject();
        DashboardDataAccessInterface dashboardRepository = new InMemoryDashDataAccessObject();

        AuthenticationOutputBoundary presenter = new AuthenticationOutputBoundary() {
            @Override
            public void prepareSuccessView(AuthenticationOutputData authenticationOutputData) {
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

        AuthenticationInputBoundary interactor = new AuthenticationInteractor(authRepository, dashboardRepository, presenter);
        AuthenticationInputData inputData = new AuthenticationInputData("alex", "macri");
        interactor.execute(inputData);
    }

}
