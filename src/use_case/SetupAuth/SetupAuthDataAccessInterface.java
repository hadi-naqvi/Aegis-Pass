package use_case.SetupAuth;

import entity.CommonAuthKey;

public interface SetupAuthDataAccessInterface {
    /**
     * Method which saves the AuthKey for the password database
     * @param key The authentication key
     */
    public void save(CommonAuthKey key);
}
