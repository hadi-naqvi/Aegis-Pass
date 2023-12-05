package data_access;

import org.json.JSONException;
import org.json.JSONObject;
import use_case.LogOut.LogOutDataAccessInterface;
import use_case.ScanItem.ScanItemDataAccessInterface;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class FileScanDataAccessObject implements ScanItemDataAccessInterface, LogOutDataAccessInterface {
    private final String API_KEY;

    // System.getenv("VT_APIKEY");

    private static final String SCAN_FILE = "https://www.virustotal.com/api/v3/files";
    private static final String REPORT_FILE = "https://www.virustotal.com/vtapi/v2/file/report";
    private int currentUserID;
    private String encryptionKey;

    /**
     * Constructor for FileScanDataAccessObject
     * @param apiKey The api key in our environment variables
     */
    public FileScanDataAccessObject(String apiKey) {
        this.API_KEY = apiKey;
    }

    /**
     * Method that scans a file using VirusTotal's API
     * @param filePath the file path of the file
     * @return returns list of user's accounts
     */
    @Override
    public String scanFile(String filePath) throws IOException, InterruptedException {
        String scanResponse = sendPostRequestFile(filePath);
        String scanId = getResponse(scanResponse);
        return scanId;

    }

    /**
     * Method that scans a url using VirusTotal's API
     * @param url the url being entered
     * @return returns list of user's accounts
     */
    @Override
    public String scanUrl(String url) throws IOException, InterruptedException, URISyntaxException {
        String scanResponse = sendPostRequestUrl(url);
        String scanId = getResponse(scanResponse);
        return scanId;
    }

    public String sendPostRequestUrl(String url) throws IOException, InterruptedException {
        String encodedUrl = URLEncoder.encode(url, "UTF-8");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.virustotal.com/api/v3/urls"))
                .header("accept", "application/json")
                .header("x-apikey", API_KEY)
                .header("content-type", "application/x-www-form-urlencoded")
                .method("POST", HttpRequest.BodyPublishers.ofString("url=" + encodedUrl))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        String self = extractSelfFromResponse(response.body());
        return self;
    }

    public String sendPostRequestFile(String filePath) throws IOException, InterruptedException {
        Path selectedFile = Path.of(filePath);

        byte[] fileContent = Files.readAllBytes(selectedFile);

        String base64EncodedContent = Base64.getEncoder().encodeToString(fileContent);

        String boundary = "---011000010111000001101001";
        String body = "--" + boundary + "\r\n" +
                "Content-Disposition: form-data; name=\"file\"; filename=\"" + selectedFile.getFileName() + "\"\r\n" +
                "Content-Type: application/pdf\r\n\r\n" +
                base64EncodedContent + "\r\n" +
                "--" + boundary + "--";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.virustotal.com/api/v3/files"))
                .header("accept", "application/json")
                .header("x-apikey", API_KEY)
                .header("content-type", "multipart/form-data; boundary=---011000010111000001101001")
                .method("POST", HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String self = extractSelfFromResponse(response.body());
        return self;
    }

    public String getResponse(String scanResponse) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(scanResponse))
                .header("accept", "application/json")
                .header("x-apikey", API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        String stats = results(response.body());
        return stats;
    }

    private String extractSelfFromResponse(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Assuming the ID is under the "data" -> "id" path in the JSON response
        String self = jsonObject.getJSONObject("data").getJSONObject("links").getString("self");

        return self;
    }

    private String results(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);

        JSONObject attributes = jsonObject.getJSONObject("data").getJSONObject("attributes");

        // Extracting stats information
        JSONObject stats = attributes.getJSONObject("stats");
        int harmless = stats.getInt("harmless");
        int suspicious = stats.getInt("suspicious");
        int malicious = stats.getInt("malicious");
        int undetected = stats.getInt("undetected");

        // Creating a comprehensive string
        StringBuilder result = new StringBuilder();
        result.append("Stats:\n");
        result.append("Harmless: ").append(harmless).append("\n");
        result.append("Suspicious: ").append(suspicious).append("\n");
        result.append("Malicious: ").append(malicious).append("\n");
        result.append("Undetected: ").append(undetected);

        return result.toString();
    }

    /**
     * Checks if a given string is a valid URL
     * @param url The URL to check
     * @return True if the URL is valid, false otherwise
     */
    public boolean isValidUrl(String url) {
        // Check if the input is not empty
        if (url.trim().isEmpty()) {
            return false;
        }

        try {
            String scanResponse = sendPostRequestUrl(url);
            JSONObject jsonResponse = new JSONObject(scanResponse);

            // Check if the JSON response contains an "error" field
            if (jsonResponse.has("error")) {
                // Check if the error message indicates an invalid URL
                JSONObject errorObject = jsonResponse.getJSONObject("error");
                String errorMessage = errorObject.getString("message");
                return errorMessage != null && errorMessage.contains("Unable to canonicalize url");
            }

            // If there is no error, consider it a valid URL
            return true;
        } catch (IOException | InterruptedException | JSONException e) {
            // Print the stack trace for debugging
            e.printStackTrace();
            return false;
        }
    }



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
}
