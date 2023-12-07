package use_case.Generate2FACode;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;

public class Generate2FACodeInteractor implements Generate2FACodeInputBoundary {
    private final Generate2FACodeOutputBoundary generate2FACodePresenter;

    /**
     * Constructor method for the generate 2FA code use case
     * @param generate2FACodePresenter The use case's presenter
     */
    public Generate2FACodeInteractor(Generate2FACodeOutputBoundary generate2FACodePresenter) {
        this.generate2FACodePresenter = generate2FACodePresenter;
    }

    /**
     * Method which contains the logic for the Generate2FACode use case (interactor) which is triggered to complete use case
     *
     * @param generate2FACodeInputData The input data for the use case interactor
     */
    @Override
    public void execute(Generate2FACodeInputData generate2FACodeInputData) {
        GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder().build();
        GoogleAuthenticator gAuth = new GoogleAuthenticator(config);
        String code = "";
        try {
            code = String.valueOf(gAuth.getTotpPassword(generate2FACodeInputData.getSecretKey()));
        }
        catch (Exception e) {
            code = "";
        }
        Generate2FACodeOutputData generate2FACodeOutputData = new Generate2FACodeOutputData(false, code);
        generate2FACodePresenter.prepareSuccessView(generate2FACodeOutputData);
    }
}
