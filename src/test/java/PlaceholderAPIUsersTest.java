import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;


public class PlaceholderAPIUsersTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void testGetUserById() {
        Response response = requestSpec.when()
                .get("/users/1")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertNotNull(response.jsonPath().getString("username"));
        Assert.assertNotNull(response.jsonPath().getString("email"));
    }

    @Test(groups = "regression")
    public void testGetUserByUsername() {
        Response response = requestSpec.queryParam("username", "Bret")
                .when()
                .get("/users")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("[0].username"), "Bret");
        Assert.assertNotNull(response.jsonPath().getString("email"));
    }

}


