package entity;

public class CommonAuthKey {
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
    public String getkey() {
        return this.key;
    }
}
