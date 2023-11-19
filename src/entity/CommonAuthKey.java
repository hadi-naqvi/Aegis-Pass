package entity;

public class CommonAuthKey implements AuthKey {
    private String key;

    /**
     * Constructor method for the AuthKey entity
     * @param key the password for the program
     */
    public CommonAuthKey(String key) {
        this.key = key;
    }

    /**
     * Getter method for the authkey/password
     * @return the password for the database
     */
    @Override
    public String getKey() {
        return this.key;
    }
}
