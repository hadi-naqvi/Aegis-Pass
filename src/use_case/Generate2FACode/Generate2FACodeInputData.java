package use_case.Generate2FACode;

public class Generate2FACodeInputData {
    final private String secretKey;

    /**
     * constructor method
     * @param secretKey the secretkey
     */
    public Generate2FACodeInputData(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * getter method for secretKey
     * @param secretKey
     * @return return the secretKey
     */
    public String getSecretKey(String secretKey){return this.secretKey;}
}
