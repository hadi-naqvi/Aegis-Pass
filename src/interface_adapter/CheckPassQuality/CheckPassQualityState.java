package interface_adapter.CheckPassQuality;

public class CheckPassQualityState {
    private int passwordQuality = 0;
    private String passwordError;

    /**
     * Constructor method for the check password quality use case
     */
    public CheckPassQualityState() {

    }

    /**
     * Setter method for the password quality
     * @param quality The password quality
     */
    public void setPasswordQuality(int quality) {
        this.passwordQuality = quality;
    }

    /**
     * Getter method for the password quality;
     * @return The password quality
     */
    public int getPasswordQuality() {
        return this.passwordQuality;
    }

    /**
     * Setter method for the password error
     * @param error The error
     */
    public void setPasswordError(String error) {
        this.passwordError = error;
    }

    /**
     * Getter method for the password error
     * @return The error
     */
    public String getPasswordError() {
        return this.passwordError;
    }
}
