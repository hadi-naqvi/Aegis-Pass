package use_case.SetupAuth;

import entity.CommonAuthKey;

public interface SetupAuthDataAccessInterface {
    public void save(CommonAuthKey key);
    public String getHashedAuthkey();
    public String getKeySalt();
}
