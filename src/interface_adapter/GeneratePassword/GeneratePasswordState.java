package interface_adapter.GeneratePassword;

public class GeneratePasswordState {
    private String passwordField;
    private int passwordQuality;
    private int passwordLength;
    private boolean lowerAlpha;
    private boolean upperAlpha;
    private boolean numericalChars;
    private boolean extendedAscii;
    private boolean punctuationOne;
    private boolean punctuationTwo;
    private boolean punctuationThree;
    private boolean punctuationFour;
    private boolean punctuationFive;
    private String alsoIncludeFrom;
    private String excludeFrom;
    private String generatePasswordError;
    public GeneratePasswordState() {

    }

    /**
     * Getter method for the password field text
     * @return The password field text
     */
    public String getPasswordField() {
        return passwordField;
    }

    /**
     * Setter method for the password field text
     * @param passwordField The password field text to be set
     */
    public void setPasswordField(String passwordField) {
        this.passwordField = passwordField;
    }

    /**
     * Getter method for the password quality number
     * @return The password quality number
     */
    public int getPasswordQuality() {
        return passwordQuality;
    }

    /**
     * Setter method for the password quality number
     * @param passwordQuality The password quality number
     */
    public void setPasswordQuality(int passwordQuality) {
        this.passwordQuality = passwordQuality;
    }

    /**
     * Getter method for the password length
     * @return The password length
     */
    public int getPasswordLength() {
        return passwordLength;
    }

    /**
     * Setter method for the password length
     * @param passwordLength The password length
     */
    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    /**
     * Getter method for the lowerAlpha charset selection
     * @return The lowerAlpha charset selection
     */
    public boolean isLowerAlpha() {
        return lowerAlpha;
    }

    /**
     * Setter method for the lowerAlpha charset selection
     * @param lowerAlpha The lowerAlpha charset selection
     */
    public void setLowerAlpha(boolean lowerAlpha) {
        this.lowerAlpha = lowerAlpha;
    }

    /**
     * Getter method for the upperAlpha charset selection
     * @return The upperAlpha charset selection
     */
    public boolean isUpperAlpha() {
        return upperAlpha;
    }

    /**
     * Setter method for the upperAlpha charset selection
     * @param upperAlpha the upperAlpha charset selection
     */
    public void setUpperAlpha(boolean upperAlpha) {
        this.upperAlpha = upperAlpha;
    }

    /**
     * Getter method for the numerical charset selection
     * @return the numerical charset selection
     */
    public boolean isNumericalChars() {
        return numericalChars;
    }

    /**
     * Setter method for the the numerical charset selection
     * @param numericalChars The the numerical charset selection
     */
    public void setNumericalChars(boolean numericalChars) {
        this.numericalChars = numericalChars;
    }

    /**
     * Getter method for the extended ASCII charset selection
     * @return The extended ASCII charset selection
     */
    public boolean isExtendedAscii() {
        return extendedAscii;
    }

    /**
     * Setter method for the extended ASCII charset selection
     * @param extendedAscii the extended ASCII charset selection
     */
    public void setExtendedAscii(boolean extendedAscii) {
        this.extendedAscii = extendedAscii;
    }

    /**
     * Getter method for the first punctuation charset selection
     * @return The first punctuation charset selection
     */
    public boolean isPunctuationOne() {
        return punctuationOne;
    }

    /**
     * Setter method for the first punctuation charset selection
     * @param punctuationOne The first punctuation charset selection
     */
    public void setPunctuationOne(boolean punctuationOne) {
        this.punctuationOne = punctuationOne;
    }

    /**
     * Getter method for the second punctuation charset selection
     * @return The second punctuation charset selection
     */
    public boolean isPunctuationTwo() {
        return punctuationTwo;
    }

    /**
     * Setter method for the second punctuation charset selection
     * @param punctuationTwo The second punctuation charset selection
     */
    public void setPunctuationTwo(boolean punctuationTwo) {
        this.punctuationTwo = punctuationTwo;
    }

    /**
     * Getter method for the third punctuation charset selection
     * @return The third punctuation charset selection
     */
    public boolean isPunctuationThree() {
        return punctuationThree;
    }

    /**
     * Setter method for the third punctuation charset selection
     * @param punctuationThree The third punctuation charset selection
     */
    public void setPunctuationThree(boolean punctuationThree) {
        this.punctuationThree = punctuationThree;
    }

    /**
     * Getter method for the fourth punctuation charset selection
     * @return The fourth punctuation charset selection
     */
    public boolean isPunctuationFour() {
        return punctuationFour;
    }

    /**
     * Setter method for the fourth punctuation charset selection
     * @param punctuationFour The fourth punctuation charset selection
     */
    public void setPunctuationFour(boolean punctuationFour) {
        this.punctuationFour = punctuationFour;
    }

    /**
     * The getter method for the fifth punctuation charset selection
     * @return The fifth punctuation charset selection
     */
    public boolean isPunctuationFive() {
        return punctuationFive;
    }

    /**
     * Setter method for the fifth punctuation charset selection
     * @param punctuationFive The fifth punctuation charset selection
     */
    public void setPunctuationFive(boolean punctuationFive) {
        this.punctuationFive = punctuationFive;
    }

    /**
     * Getter method for the also included charset selection
     * @return The also included charset selection
     */
    public String getAlsoIncludeFrom() {
        return alsoIncludeFrom;
    }

    /**
     * Setter method for the also included charset selection
     * @param alsoIncludeFrom The also included charset selection
     */
    public void setAlsoIncludeFrom(String alsoIncludeFrom) {
        this.alsoIncludeFrom = alsoIncludeFrom;
    }

    /**
     * Getter method for the excluded charset selection
     * return The excluded charset selection
     */
    public String getExcludeFrom() {
        return excludeFrom;
    }

    /**
     * Setter method for the excluded charset selection
     * return The excluded charset selection
     */
    public void setExcludeFrom(String excludeFrom) {
        this.excludeFrom = excludeFrom;
    }

    /**
     * Getter method for the generate password error
     * @return The generate password error
     */
    public String getGeneratePasswordError() {
        return this.generatePasswordError;
    }

    /**
     * Setter method for the generate password error
     * @param error The generate password error
     */
    public void setGeneratePasswordError(String error) {
        this.generatePasswordError = error;
    }
}
