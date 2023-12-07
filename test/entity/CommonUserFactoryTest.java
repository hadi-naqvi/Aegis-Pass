package entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommonUserFactoryTest {

    /**
     * Test method for CommonUserFactory to ensure that the CommonUser objects it creates match the result of making it
     * manually (this would be a representation invariant, that the CommonUser object remains as it would in the
     * CommonUserFactory)
     */
    @Test
    public void createTest() {
        String username = "ajdlfsdfmnewrmwer125--=-##(";
        String password = "icxiovbwnerijzllfjad324??";
        User testUser = new CommonUser(username, password);
        UserFactory factory = new CommonUserFactory();
        assertEquals(testUser.getUsername(), factory.create(username, password).getUsername());
        assertEquals(testUser.getPassword(), factory.create(username, password).getPassword());
    }

}
