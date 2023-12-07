package use_case.CheckPassQuality;

public class CheckPassQualityInputData {
    private String password;

    /**
     * Constructor method for the input data of the check password quality use case
     * @param password The password bneing checked
     */
    public CheckPassQualityInputData(String password) {
        this.password = password;
    }

    /**
     * Setter method for the password of the input data
     * @param password The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for the password of the input data
     * @return The password
     */
    public String getPassword() {
        return this.password;
    }
}
