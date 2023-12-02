package use_case.Authentication;


import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.bouncycastle.util.encoders.Hex;
import use_case.Dashboard.DashboardDataAccessInterface;
import use_case.SetupAuth.SetupAuthOutputData;

import java.nio.charset.StandardCharsets;

public class AuthenticationInteractor implements AuthenticationInputBoundary {
    final private AuthenticationDataAccessInterface userDataAccessObject;
    final private DashboardDataAccessInterface dashboardDataAccessObject;
    final private AuthenticationOutputBoundary authenticationPresenter;

    /**
     * Constructor method for the use case interactor for the authentication use case
     * @param userDataAccessObject The data access object which provides access to the stored data required
     * @param authenticationPresenter The presenter for the authentication use case
     */
    public AuthenticationInteractor(AuthenticationDataAccessInterface userDataAccessObject,
                                    DashboardDataAccessInterface dashboardDataAccessObject,
                                    AuthenticationOutputBoundary authenticationPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.dashboardDataAccessObject = dashboardDataAccessObject;
        this.authenticationPresenter = authenticationPresenter;
    }

    /**
     * Method which contains the logic for the authentication use case (interactor) which is triggered to complete use case
     * @param authenticationInputData The input data for the authentication use case
     */
    @Override
    public void execute(AuthenticationInputData authenticationInputData) {
        if (!(userDataAccessObject.validate(authenticationInputData.getUsername(),
                authenticationInputData.getPassword()))) {
            authenticationPresenter.prepareFailView("Passwords is incorrect.");
        }
        else {
            AuthenticationOutputData authenticationOutputData = new AuthenticationOutputData(true);

            // Update dashboard DAO to include the user id and encryption key for the logged in user
            String key = deriveKey(authenticationInputData.getPassword(), userDataAccessObject.getUserSalt(authenticationInputData.getUsername()));
            dashboardDataAccessObject.setCurrentUserID(userDataAccessObject.getUserID(authenticationInputData.getUsername()));
            dashboardDataAccessObject.setEncryptionKey(key);

            authenticationPresenter.prepareSuccessView(authenticationOutputData);
        }
    }

    /**
     * KDF (Key Deriviation Function) to generate a the encryption key for the signed in user after they have logged in
     * @param password The password
     * @param salt The salt
     * @return The encryption key
     */
    private static String deriveKey(String password, String salt) {
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id);
        builder.withSalt(Hex.decode(salt));
        builder.withParallelism(8);
        builder.withMemoryAsKB(65536);
        builder.withIterations(4);

        Argon2Parameters parameters = builder.build();

        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(parameters);

        char[] passwordChars = password.toCharArray();
        byte[] key = new byte[32];
        generator.generateBytes(passwordChars, key, 0, key.length);

        return Hex.toHexString(key);
    }
}