package use_case.ScanItem;

import data_access.InMemoryFileScanDataAccessObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class ScanItemInteractorTest {

    @Test
    public void successFileTest() {
        ScanItemDataAccessInterface scanRepository = new InMemoryFileScanDataAccessObject();

        ScanItemOutputBoundary presenter = new ScanItemOutputBoundary() {
            @Override
            public void prepareSuccessView(ScanItemOutputData scanItemOutputData) {

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

        ScanItemInputBoundary interactor = new ScanItemInteractor(scanRepository, presenter);
        ScanItemInputData inputData = new ScanItemInputData("a", "");
        interactor.scanFile(inputData);
    }

    @Test
    public void failureFileTest() {
        ScanItemDataAccessInterface scanRepository = new InMemoryFileScanDataAccessObject();

        ScanItemOutputBoundary presenter = new ScanItemOutputBoundary() {
            @Override
            public void prepareSuccessView(ScanItemOutputData scanItemOutputData) {
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

        ScanItemInputBoundary interactor = new ScanItemInteractor(scanRepository, presenter);
        ScanItemInputData inputData = new ScanItemInputData("", "");
        interactor.scanFile(inputData);
    }

    @Test
    public void successUrlTest() throws IOException, InterruptedException {
        ScanItemDataAccessInterface scanRepository = new InMemoryFileScanDataAccessObject();

        ScanItemOutputBoundary presenter = new ScanItemOutputBoundary() {
            @Override
            public void prepareSuccessView(ScanItemOutputData scanItemOutputData) {

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

        ScanItemInputBoundary interactor = new ScanItemInteractor(scanRepository, presenter);
        ScanItemInputData inputData = new ScanItemInputData("x", "");
        interactor.scanUrl(inputData);
    }

    @Test
    public void failureUrlTest() throws IOException, InterruptedException {
        ScanItemDataAccessInterface scanRepository = new InMemoryFileScanDataAccessObject();

        ScanItemOutputBoundary presenter = new ScanItemOutputBoundary() {
            @Override
            public void prepareSuccessView(ScanItemOutputData scanItemOutputData) {
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

        ScanItemInputBoundary interactor = new ScanItemInteractor(scanRepository, presenter);
        ScanItemInputData inputData = new ScanItemInputData("", "");
        interactor.scanUrl(inputData);
    }

}
