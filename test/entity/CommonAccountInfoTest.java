package entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommonAccountInfoTest {

    private String title = "jsdlkfjsd";
    private String username = "sdfbv";
    private String password = "password f**(*(((";
    private String secretKey = "secrets";
    private String url = "http://stuff.com";
    private String iconURL = "sdfadfasdfadsf..../////";
    private String date = "yyyy-MM-dd HH:mm:ss";
    private String notes = "this is a test case";
    private AccountInfo accountInfo = new CommonAccountInfo();

    /**
     * Test method for CommonAccountInfo's setTitle and getTitle methods
     */
    @Test
    public void titleTest() {
        accountInfo.setTitle(title);
        assertEquals(title, accountInfo.getTitle());
    }

    /**
     * Test method for CommonAccountInfo's setUsername and getUsername methods
     */
    @Test
    public void usernameTest() {
        accountInfo.setUsername(username);
        assertEquals(username, accountInfo.getUsername());
    }

    /**
     * Test method for CommonAccountInfo's setPassword and getPassword methods
     */
    @Test
    public void passwordTest() {
        accountInfo.setPassword(password);
        assertEquals(password, accountInfo.getPassword());
    }

    /**
     * Test method for CommonAccountInfo's setSecretKey and getSecretKey methods
     */
    @Test
    public void secretKeyTest() {
        accountInfo.setSecretKey(secretKey);
        assertEquals(secretKey, accountInfo.getSecretKey());
    }

    /**
     * Test method for CommonAccountInfo's setURL and getURL methods
     */
    @Test
    public void urlTest() {
        accountInfo.setURL(url);
        assertEquals(url, accountInfo.getURL());
    }

    /**
     * Test method for CommonAccountInfo's setIconURL and getIconURL methods
     */
    @Test
    public void iconURLTest() {
        accountInfo.setIconURL(iconURL);
        assertEquals(iconURL, accountInfo.getIconURL());
    }

    /**
     * Test method for CommonAccountInfo's setDate and getDate methods
     */
    @Test
    public void dateTest() {
        accountInfo.setDate(date);
        assertEquals(date, accountInfo.getDate());
    }

    /**
     * Test method for CommonAccountInfo's setNotes and getNotes methods
     */
    @Test
    public void notesTest() {
        accountInfo.setNotes(notes);
        assertEquals(notes, accountInfo.getNotes());
    }

}
