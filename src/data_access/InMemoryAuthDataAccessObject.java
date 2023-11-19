package data_access;

import entity.AuthKey;
import entity.AuthKeyFactory;
import entity.CommonAuthKey;
import use_case.SetupAuth.SetupAuthDataAccessInterface;

public class InMemoryAuthDataAccessObject implements SetupAuthDataAccessInterface {

    private String authKey;

    /**
     * Method which saves the AuthKey for the password database
     *
     * @param key The authentication key
     */
    @Override
    public void save(CommonAuthKey key) {
        this.authKey = key.getKey();
    }
}
