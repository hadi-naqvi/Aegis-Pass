package data_access;

import com.mysql.cj.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.CheckBreach.CheckBreachDataAccessInterface;
import use_case.LogOut.LogOutDataAccessInterface;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileBreachDataAccessObject implements CheckBreachDataAccessInterface, LogOutDataAccessInterface {

    private final String API_KEY;
    private int currentUserID;
    private String encryptionKey;

    /**
     * Constructor for the Data Access Object of the Check Breach use case
     * @param api_key the API key for the HaveIBeenPWNED API
     */
    public FileBreachDataAccessObject(String api_key) {
        this.API_KEY = api_key;
    }

    /**
     * Method that checks an email for breaches using HaveIBeenPWNED's API
     *
     * @param email the email to be checked for
     * @return the breaches that the email has been in
     */
    @Override
    public String checkEmail(String email) throws IOException, InterruptedException, URISyntaxException {
        return getEmailResponse(email);
    }

    private String getEmailResponse(String email) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://haveibeenpwned.com/api/v3/breachedaccount/" + email))
                    .header("accept", "application/json")
                    .header("hibp-api-key", API_KEY)
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return getEmailBreachesFromResponse(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getEmailBreachesFromResponse(String responseBody) {
        try {
            if (responseBody.equals("")) {
                return "We couldn't find your email in any breaches";
            } else if (responseBody.contains("Rate limit is exceeded")) {
                return "Please try again in a few seconds";
            }

            JSONArray jsonArray = new JSONArray(responseBody.trim());
            String emailBreaches;
            if (jsonArray.isEmpty()) {
                emailBreaches = "We couldn't find your email in any breaches";
            } else {
                emailBreaches = "Your password was found in breaches from: ";
                for (int i = 0; i < jsonArray.length(); i++) {
                    emailBreaches += jsonArray.getJSONObject(i).get("Name") + ", ";
                }
                emailBreaches = emailBreaches.substring(0, emailBreaches.length() - 2);
            }
            return emailBreaches;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method that checks password for breaches using HaveIBeenPWNED's API
     *
     * @param password the password to be checked for
     * @return the breaches that the password has been in
     */
    @Override
    public String checkPassword(String password) throws IOException, InterruptedException, URISyntaxException {
        return getPasswordResponse(password);
    }

    private String getPasswordResponse(String password) {
        try {
            String hashedPassword = sha1Hash(password);
            String hashPrefix = hashedPassword.substring(0, 5).toUpperCase();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.pwnedpasswords.com/range/" + hashPrefix))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            String remainingHash = hashedPassword.substring(5).toUpperCase();
            return getPasswordBreachesFromResponse(responseBody, remainingHash);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getPasswordBreachesFromResponse(String responseBody, String remainingHash) {
        int breaches = 0;
        String[] lines = responseBody.split("\\r?\\n");
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2 && parts[0].equals(remainingHash)) {
                breaches = Integer.parseInt(parts[1]);
            }
        }

        return "Your password has been breached " + breaches + " times";
    }

    private static String sha1Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = digest.digest(input.getBytes());
        StringBuilder hashHex = new StringBuilder();
        for (byte b : hashBytes) {
            hashHex.append(String.format("%02x", b));
        }
        return hashHex.toString().toUpperCase();
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
