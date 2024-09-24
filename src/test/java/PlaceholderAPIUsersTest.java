import org.example.clients.UsersClient;
import org.example.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaceholderAPIUsersTest extends BaseTest {

    private UsersClient usersClient;

    @BeforeClass
    public void setup() {
        usersClient = new UsersClient(requestSpec);
    }

    @Test(groups = {"smoke", "regression"})
    public void testGetUserById() {
        User user = usersClient.getUserById(1);

        Assert.assertEquals(user.getId(), 1);
        Assert.assertNotNull(user.getUsername());
        Assert.assertNotNull(user.getEmail());
    }

    @Test(groups = "regression")
    public void testGetUserByUsername() {
        User[] users = usersClient.getUserByUsername("Bret");

        Assert.assertTrue(users.length > 0);
        Assert.assertEquals(users[0].getUsername(), "Bret");
        Assert.assertNotNull(users[0].getEmail());
    }
}