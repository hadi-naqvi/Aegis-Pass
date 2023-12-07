package use_case.CheckPassQuality;

public class CheckPassQualityInteractor implements CheckPassQualityInputBoundary {
    private static final String LOWER_ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMERICAL_CHARS = "0123456789";
    private static final String SPECIAL_CHARS = ".,:;#$%@^'~\\/|_-<*+?!=[{()}]";

    private final CheckPassQualityOutputBoundary checkPassQualityPresenter;
    public CheckPassQualityInteractor(CheckPassQualityOutputBoundary checkPassQualityPresenter) {
        this.checkPassQualityPresenter = checkPassQualityPresenter;
    }

    /**
     * Method which contains the logic for the check password quality use case (interactor) which is triggered to complete use case
     *
     * @param checkPassQualityInputData The input data for the use case interactor
     */
    @Override
    public void execute(CheckPassQualityInputData checkPassQualityInputData) {
        if (checkPassQualityInputData.getPassword().isEmpty()) {
            checkPassQualityPresenter.prepareFailView("The password field is empty.");
        }
        else {
            int passwordQuality = calculatePasswordQuality(checkPassQualityInputData.getPassword());
            CheckPassQualityOutputData checkPassQualityOutputData = new CheckPassQualityOutputData(passwordQuality);
            checkPassQualityPresenter.prepareSuccessView(checkPassQualityOutputData);
        }
    }

    /**
     * Method which calcualtes the bit entropy of a password
     * @param password The password
     * @return The bit entropy
     */
    private int calculatePasswordQuality(String password) {
        double entropy = 0;

        for (char c : password.toCharArray()) {
            if (LOWER_ALPHA.indexOf(c) != -1) {
                entropy += Math.log(LOWER_ALPHA.length()) / Math.log(2);
            }
            else if (UPPER_ALPHA.indexOf(c) != -1) {
                entropy += Math.log(UPPER_ALPHA.length()) / Math.log(2);
            }
            else if (NUMERICAL_CHARS.indexOf(c) != -1) {
                entropy += Math.log(NUMERICAL_CHARS.length()) / Math.log(2);
            }
            else if (SPECIAL_CHARS.indexOf(c) != -1) {
                entropy += Math.log(SPECIAL_CHARS.length()) / Math.log(2);
            }
            else {
                // Character not found in specified charsets, assume it's part of Extended ASCII
                entropy += Math.log(128) / Math.log(2); // Assuming Extended ASCII has 128 characters
            }
        }

        return (int) entropy;
    }
}
