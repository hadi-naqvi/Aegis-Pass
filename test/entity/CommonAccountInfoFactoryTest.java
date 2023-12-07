package entity;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class CommonAccountInfoFactoryTest {

    /**
     * Test method for CommonAccountInfoFactory to ensure that the AccountInfo objects it creates match the result of
     * making it manually (this would be a representation invariant, that the AccountInfo object remains as it would in
     * the CommonAccountInfoFactory)
     */
    @Test
    public void createTest() {
        String title = "test title";
        String username = "test username";
        String password = "test password";
        String secretKey = "test secretKey";
        String url = "test url";
        String iconUrl = "test iconUrl";
        String date = "test date";
        String notes = "test notes";
        AccountInfo accountInfo = new CommonAccountInfo(title, username, password, secretKey, url, iconUrl, date, notes);
        AccountInfoFactory accountInfoFactory = new CommonAccountInfoFactory();
        assertTrue(compareAccountInfo(accountInfo, accountInfoFactory.create(title, username, password, secretKey, url,
                iconUrl, date, notes)));
    }

    /**
     * Test method for CommonAccountInfoFactory's alternative constructor method that creates an empty CommonAccountInfo
     * (one with all null attributes)
     */
    @Test
    public void emptyCreateTest() {
        AccountInfo accountInfo = new CommonAccountInfo();
        AccountInfoFactory accountInfoFactory = new CommonAccountInfoFactory();
        assertTrue(compareAccountInfo(accountInfo, accountInfoFactory.create()));
    }

    /**
     * Method to see if all attributes of each account passed in are equal. Returns true if they are
     * @param account1 the first account to compare
     * @param account2 the second account to compare
     * @return true if the accounts have equal attributes, else false
     */
    public boolean compareAccountInfo(AccountInfo account1, AccountInfo account2) {
        return Objects.equals(account1.getTitle(), account2.getTitle()) &&
                Objects.equals(account1.getUsername(), account2.getUsername()) &&
                Objects.equals(account1.getPassword(), account2.getPassword()) &&
                Objects.equals(account1.getSecretKey(), account2.getSecretKey()) &&
                Objects.equals(account1.getURL(), account2.getURL()) &&
                Objects.equals(account1.getIconURL(), account2.getIconURL()) &&
                Objects.equals(account1.getDate(), account2.getDate()) &&
                Objects.equals(account1.getNotes(), account2.getNotes());
    }
}
