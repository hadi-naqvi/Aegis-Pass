package use_case.CheckBreach;

public class CheckBreachInputData {

    final private String email;
    final private String password;

    /**
     * Constructor method for the Input Data of the Check Breach use case
     * @param email the email string to be checked for breaches
     * @param password the password string to be checked for breaches
     */
    public CheckBreachInputData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Getter method for the email to be checked for breaches
     * @return the email string to be checked for breaches
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter method for the password to be checked for breaches
     * @return the password string to be checked for breaches
     */
    public String getPassword() {
        return password;
    }
}
