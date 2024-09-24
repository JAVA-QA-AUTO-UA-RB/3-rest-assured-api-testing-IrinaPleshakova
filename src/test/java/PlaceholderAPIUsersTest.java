import io.restassured.RestAssured;
import org.example.models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class PlaceholderAPIUsersTest extends BaseTest {

@Test(groups = {"smoke", "regression"})
public void testGetUserById() {
    User user = RestAssured.given(requestSpec)
            .when()
            .get("/users/1")
            .then()
            .statusCode(200)
            .extract().as(User.class);

    Assert.assertEquals(user.getId(), 1);
    Assert.assertNotNull(user.getUsername());
    Assert.assertNotNull(user.getEmail());
}

    @Test(groups = "regression")
    public void testGetUserByUsername() {
        User[] users = RestAssured.given(requestSpec)
                .queryParam("username", "Bret")
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().as(User[].class);

        Assert.assertEquals(users[0].getUsername(), "Bret");
        Assert.assertNotNull(users[0].getEmail());
    }
}


