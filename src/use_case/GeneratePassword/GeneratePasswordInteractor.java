package use_case.GeneratePassword;

import use_case.Dashboard.DashboardInputData;

import java.security.SecureRandom;

public class GeneratePasswordInteractor implements GeneratePasswordInputBoundary {
    private static final String LOWER_ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMERICAL_CHARS = "0123456789";
    private static final String PUNCTUATION_ONE = ".,:;";
    private static final String PUNCTUATION_TWO = "#$%@^'~";
    private static final String PUNCTUATION_THREE = "\\/|_-";
    private static final String PUNCTUATION_FOUR = "<*+?!=";
    private static final String PUNCTUATION_FIVE = "[{()}]";
    private final GeneratePasswordOutputBoundary generatePasswordPresenter;

    public GeneratePasswordInteractor(GeneratePasswordOutputBoundary generatePasswordPresenter) {
        this.generatePasswordPresenter = generatePasswordPresenter;
    }

    /**
     * Method which contains the logic for the Generate Password use case (interactor) which is triggered to complete use case
     *
     * @param generatePasswordInputData The input data for the use case interactor
     */
    @Override
    public void execute(GeneratePasswordInputData generatePasswordInputData) {
        if (!generatePasswordInputData.isLowerAlpha() && !generatePasswordInputData.isUpperAlpha() &&
                !generatePasswordInputData.isNumericalChars() && !generatePasswordInputData.isExtendedAscii() &&
                !generatePasswordInputData.isPunctuationOne() && !generatePasswordInputData.isPunctuationTwo() &&
                !generatePasswordInputData.isPunctuationThree() && !generatePasswordInputData.isPunctuationFour() &&
                !generatePasswordInputData.isPunctuationFive() &&
                        generatePasswordInputData.getAlsoIncludeFrom().equals("") &&
                        generatePasswordInputData.getExcludeFrom().equals("")) {
            generatePasswordPresenter.prepareFailView("You have not selected any characters for your character set.");
        }
        else {
            String generatedPassword = generatePassword(generatePasswordInputData.getPasswordLength(),
                    generatePasswordInputData.isLowerAlpha(), generatePasswordInputData.isUpperAlpha(),
                    generatePasswordInputData.isNumericalChars(), generatePasswordInputData.isExtendedAscii(),
                    generatePasswordInputData.isPunctuationOne(), generatePasswordInputData.isPunctuationTwo(),
                    generatePasswordInputData.isPunctuationThree(), generatePasswordInputData.isPunctuationFour(),
                    generatePasswordInputData.isPunctuationFive(), generatePasswordInputData.getAlsoIncludeFrom(),
                    generatePasswordInputData.getExcludeFrom());
            GeneratePasswordOutputData generatePasswordOutputData = new GeneratePasswordOutputData(false, generatedPassword);
            generatePasswordPresenter.prepareSuccessView(generatePasswordOutputData);
        }
    }

    /**
     * Method which generates and returns a password given certain charset specifications
     * @param length The password length
     * @param lowerAlpha Lowercase charset
     * @param upperAlpha Uppercase charset
     * @param numericalChars Numerical charset
     * @param extendedAscii Extended ASCII charset
     * @param punctuationOne PunctuationOne charset
     * @param punctuationTwo PunctuationTwo charset
     * @param punctuationThree PunctuationThree charset
     * @param punctuationFour PunctuationFour charset
     * @param punctuationFive PunctuationFive charset
     * @param alsoIncludeFrom Extra charset inclusion
     * @param excludeFrom Charset exlusions
     * @return A randomly generated password of a given length using the specified charsets
     */
    private static String generatePassword(int length, boolean lowerAlpha, boolean upperAlpha, boolean numericalChars,
                                          boolean extendedAscii, boolean punctuationOne, boolean punctuationTwo,
                                          boolean punctuationThree, boolean punctuationFour, boolean punctuationFive,
                                          String alsoIncludeFrom, String excludeFrom) {
        StringBuilder masterCharset = new StringBuilder();

        if (lowerAlpha) masterCharset.append(LOWER_ALPHA);
        if (upperAlpha) masterCharset.append(UPPER_ALPHA);
        if (numericalChars) masterCharset.append(NUMERICAL_CHARS);
        if (punctuationOne) masterCharset.append(PUNCTUATION_ONE);
        if (punctuationTwo) masterCharset.append(PUNCTUATION_TWO);
        if (punctuationThree) masterCharset.append(PUNCTUATION_THREE);
        if (punctuationFour) masterCharset.append(PUNCTUATION_FOUR);
        if (punctuationFive) masterCharset.append(PUNCTUATION_FIVE);

        if (extendedAscii) {
            for (int i = 32; i <= 126; i++) {
                masterCharset.append((char) i);
            }
            for (int i = 160; i <= 255; i++) {
                masterCharset.append((char) i);
            }
        }

        if (alsoIncludeFrom != null && !alsoIncludeFrom.isEmpty()) {
            masterCharset.append(alsoIncludeFrom);
        }

        if (excludeFrom != null && !excludeFrom.isEmpty()) {
            masterCharset = new StringBuilder(masterCharset.toString().replaceAll("[" + excludeFrom + "]", ""));
        }

        return generateRandomPassword(masterCharset.toString(), length);
    }

    /**
     * Given a charset and a length, this method securely generates a random string/password
     * @param charset A charset
     * @param length A length
     * @return A randomly generated password with the specified length containing only chars in the specified charset
     */
    private static String generateRandomPassword(String charset, int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charset.length());
            password.append(charset.charAt(randomIndex));
        }

        return password.toString();
    }
}
