package data_access;

import org.json.JSONArray;
import org.json.JSONObject;
import use_case.GenerateEmail.GenerateEmailDataAccessInterface;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GenEmailDataAccessObject implements GenerateEmailDataAccessInterface {

    /**
     * Constructor for FileGenEmailDataAccessObject
     */
    public GenEmailDataAccessObject() {
    }

    /**
     * Method that creates a temporary email using mail.gw API
     * @param accountName the name of the user's temporary email
     * @return returns account which is created
     */
    @Override
    public String createEmail(String accountName, String pass) throws IOException, InterruptedException {
        String domainResponse = getDomain();
        String accountResponse = getAccount(accountName+"@"+domainResponse, pass);

        return accountResponse;

    }

    /**
     * Method which uses mail.gw's API to get possible domains
     * @return returns the first possible domain name
     */
    public String getDomain() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.mail.gw/domains?page=1"))
                .header("accept", "application/ld+json")
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        String name = extractDomains(response.body());
        return name;
    }

    /**
     * Helper method for extracting the domain name from the GET request
     * @param jsonResponse the json that is returned after the GET request
     * @return returns the domain name from the GET request
     */
    private String extractDomains(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);

        JSONArray hydraMember = jsonObject.getJSONArray("hydra:member");
        String domain = hydraMember.getJSONObject(0).getString("domain");

        return domain;
    }

    /**
     * Method which uses mail.gw's API to create an email account
     * @return returns the generated email
     */
    public String getAccount(String accountName, String pass) throws IOException, InterruptedException {
        String jsonPayload = "{\"address\": \"" + accountName + "\", \"password\": \"" + pass + "\"}";


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.mail.gw/accounts"))
                .header("accept", "application/ld+json")
                .header("Content-Type", "application/ld+json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        String name = extractAccount(response.body());
        return name;
    }

    /**
     * Helper method for extracting the email account from the GET request
     * @param jsonResponse the json that is returned after the GET request
     * @return returns the email account from the GET request
     */
    private String extractAccount(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Extract the value of the "address" field
        String address = jsonObject.getString("address");

        return address;
    }

}
