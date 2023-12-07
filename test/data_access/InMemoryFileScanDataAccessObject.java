package data_access;

import use_case.LogOut.LogOutDataAccessInterface;
import use_case.ScanItem.ScanItemDataAccessInterface;

import java.io.IOException;
import java.net.URISyntaxException;

public class InMemoryFileScanDataAccessObject implements ScanItemDataAccessInterface, LogOutDataAccessInterface {

    private int currentUserID = 1;
    private String encryptionKey = "$2a$15$sDwsCyD.ZiWm3zSh1lzR0e";

    /**
     * Setter method for the currently signed in user's id
     *
     * @param userID the user id
     */
    @Override
    public void setCurrentUserID(int userID) {
        this.currentUserID = userID;
    }

    /**
     * Setter method for the encryption key
     *
     * @param key the encryption key
     */
    @Override
    public void setEncryptionKey(String key) {
        this.encryptionKey = key;
    }

    /**
     * Method that scans a file using VirusTotal's API
     *
     * @param filePath the file path of the file
     * @return returns list of user's accounts
     */
    @Override
    public String scanFile(String filePath) throws IOException, InterruptedException {
        if (filePath.equals("a")) {
            return generateResponse(3, 5, 1123, 6);
        } else {
            return generateResponse(0, 0, 0, 0);
        }
    }

    private String generateResponse(int harmless, int suspicious, int malicious, int undetected) {
        return "Stats:\n" +
                "Harmless: " + harmless + "\n" +
                "Suspicious: " + suspicious + "\n" +
                "Malicious: " + malicious + "\n" +
                "Undetected: " + undetected;
    }

    /**
     * Method that scans a url using VirusTotal's API
     *
     * @param filePath the name of the url
     * @return returns list of user's accounts
     */
    @Override
    public String scanUrl(String filePath) throws IOException, InterruptedException, URISyntaxException {
        if (filePath.equals("x")) {
            return generateResponse(5, 42, 1, 0);
        } else {
            return generateResponse(0, 0, 0, 0);
        }
    }

    /**
     * Checks if a given string is a valid URL
     *
     * @param url The URL to check
     * @return True if the URL is valid, false otherwise
     */
    @Override
    public boolean isValidUrl(String url) throws IOException, InterruptedException {
        // for test purposes, this is valid
        return true;
    }

}
