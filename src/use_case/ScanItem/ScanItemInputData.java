package use_case.ScanItem;

public class ScanItemInputData {

    final private String filePath;
    final private String url;


    /**
     * Constructor method for the Scan Item use case's input data
     *
     * @param filePath The path of the file being entered during scan file
     * @param url
     */
    public ScanItemInputData(String filePath, String url) {
        this.filePath = filePath;
        this.url = url;
    }

    /**
     * Getter method for the file path being entered during scanning
     * @return The file path being entered during scan file
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Getter method for the url being entered during scanning
     * @return The url being entered during scan url
     */
    public String getUrl() { return url; }
}
