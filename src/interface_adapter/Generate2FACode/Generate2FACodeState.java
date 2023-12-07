package interface_adapter.Generate2FACode;

import entity.AccountInfo;

import java.util.List;

public class Generate2FACodeState {
    private String faCode = null;

    /**
     * Constructor method for the Generate2FACode's view state
     * @param copy a copy of the Generate2FACode state
     */
    public Generate2FACodeState(Generate2FACodeState copy){
        this.faCode = copy.faCode;
    }

    /**
     * Alternative constructor method for the Generate2FACode state for no copy
     * which keeps attributes initialized as null/empty strings
     */
    public Generate2FACodeState(){

    }

    /**
     * Getter method for the user's 2FAcode
     * @return The user's 2FAcode
     */
    public String getFaCode() { return this.faCode; }


    /**
     * Setter method for the user's 2FAcode
     * @param faCode the user's 2FAcode
     */
    public void setFaCode(String faCode){this.faCode = faCode;}
}
