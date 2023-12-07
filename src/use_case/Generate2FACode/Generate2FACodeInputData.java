package use_case.Generate2FACode;

public class Generate2FACodeInputData {
    final private String secretKey;

    /**
     * Constructor method
     * @param secretKey the secretkey
     */
    public Generate2FACodeInputData(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Getter method for secretKey
     * @return return the secretKey
     */
    public String getSecretKey() {
        return this.secretKey;}
}
