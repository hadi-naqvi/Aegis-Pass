package entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonUserTest {

    private User commonUser = new CommonUser("fjslkdfjalkdflfbgn", "iqwouehjfknbcxoiuhvas");

    /**
     * Test method for CommonUser.getUsername()
     */
    @Test
    public void getUsernameTest() {
        assertEquals("fjslkdfjalkdflfbgn", commonUser.getUsername());
    }

    /**
     * Test method for CommonUser.getPassword()
     */
    @Test
    public void getPasswordTest() {
        assertEquals("iqwouehjfknbcxoiuhvas", commonUser.getPassword());
    }

}
