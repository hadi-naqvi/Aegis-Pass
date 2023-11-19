package entity;

import java.time.LocalDateTime;

public class CommonAuthKeyFactory implements AuthKeyFactory {

    @Override
    public AuthKey create(String key) {
        return new CommonAuthKey(key);
    }
}
