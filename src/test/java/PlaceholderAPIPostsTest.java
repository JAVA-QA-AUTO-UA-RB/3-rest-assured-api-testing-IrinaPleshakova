import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PlaceholderAPIPostsTest extends BaseTest {

    // Під словом "Post" в назвах методів мається на увазі назва ресурсу (не плутати з HTTP методом POST
    @Test(groups = {"smoke", "regression"})
    public void testGetPost() {
        Response response = requestSpec.when()
                .get("/posts/1")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertNotNull(response.jsonPath().getString("title"));
        Assert.assertNotNull(response.jsonPath().getString("body"));
    }

    @Test(groups = "regression")
    public void testCreatePost() {
        String newPostJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";

        Response response = requestSpec.body(newPostJson)
                .when()
                .post("/posts")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getInt("id"));
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
        Assert.assertEquals(response.jsonPath().getString("body"), "bar");
    }

    @Test(groups = "regression")
    public void testUpdatePost() {
        String updatedPostJson = "{\"id\":1,\"title\":\"updated title\",\"body\":\"updated body\",\"userId\":1}";

        Response response = requestSpec.body(updatedPostJson)
                .when()
                .put("/posts/1")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "updated title");
        Assert.assertEquals(response.jsonPath().getString("body"), "updated body");
    }

    @Test(groups = "regression")
    public void testDeletePost() {
        Response response = requestSpec
                .when()
                .delete("/posts/1")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Response getResponse = requestSpec.when().get("/posts/1");
        Assert.assertEquals(getResponse.getStatusCode(), 404);
    }
}


