package use_case.SetupAuth;
public class SetupAuthInputData {
    final private String password;
    final private String repeatPassword;

    public SetupAuthInputData(String password, String repeatPassword) {
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRepeatPassword() {
        return this.repeatPassword;
    }
}
