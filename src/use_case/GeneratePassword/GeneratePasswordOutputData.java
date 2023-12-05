package use_case.GeneratePassword;

import entity.AccountInfo;

import java.util.List;

public class GeneratePasswordOutputData {
    private final boolean useCaseFailed;
    private String password;

    /**
     * Consturctor method for the generate password output data
     * @param useCaseFailed Whether the use case failed or not (i.e. failed authentication)
     */
    public GeneratePasswordOutputData(boolean useCaseFailed, String password) {
        this.useCaseFailed = useCaseFailed;
        this.password = password;
    }

    /**
     * Getter method for the password output
     * @return The newly generated password
     */
    public String getPassword(){
        return this.password;
    }
}
