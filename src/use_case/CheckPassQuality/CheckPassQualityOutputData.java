package use_case.CheckPassQuality;

public class CheckPassQualityOutputData {
    private int passQuality;

    /**
     * Constructor method for the check password quality use case output data
     * @param quality The password quality
     */
    public CheckPassQualityOutputData(int quality) {
        this.passQuality = quality;
    }

    /**
     * Setter method for the password quality
     * @param quality The password quality
     */
    public void setPassQuality(int quality) {
        this.passQuality = quality;
    }

    /**
     * Getter method for the password quality
     * @return The password quality
     */
    public int getPassQuality() {
        return this.passQuality;
    }
}
