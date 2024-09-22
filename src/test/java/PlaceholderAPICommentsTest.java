import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;


public class PlaceholderAPICommentsTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void testGetCommentById() {
        Response response = requestSpec.when()
                .get("/comments/1")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("name"));
    }

    @Test(groups = "regression")
    public void testGetCommentsByPostId() {
        Response response = requestSpec.queryParam("postId", 1)
                .when()
                .get("/comments")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0);
    }

}


