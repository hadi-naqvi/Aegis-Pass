package use_case.ScanItem;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ScanItemDataAccessInterface {

    /**
     * Method that scans a file using VirusTotal's API
     * @param filePath the file path of the file
     * @return returns list of user's accounts
     */
    public String scanFile(String filePath) throws IOException, InterruptedException;

    /**
     * Method that scans a url using VirusTotal's API
     * @param filePath the name of the url
     * @return returns list of user's accounts
     */
    public String scanUrl(String filePath) throws IOException, InterruptedException, URISyntaxException;

    /**
     * Checks if a given string is a valid URL
     * @param url The URL to check
     * @return True if the URL is valid, false otherwise
     */
    public boolean isValidUrl(String url);
}
