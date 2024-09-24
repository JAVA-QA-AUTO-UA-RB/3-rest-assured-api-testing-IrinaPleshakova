import io.restassured.RestAssured;
import org.example.models.Comment;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class PlaceholderAPICommentsTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void testGetCommentById() {
        Comment comment = RestAssured.given(requestSpec)
                .when()
                .get("/comments/1")
                .then()
                .statusCode(200)
                .extract().as(Comment.class);

        Assert.assertNotNull(comment.getName());
        Assert.assertEquals(comment.getId(), 1);
        Assert.assertNotNull(comment.getBody());
    }

    @Test(groups = "regression")
    public void testGetCommentsByPostId() {
        Comment[] comments = RestAssured.given(requestSpec)
                .queryParam("postId", 1)
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .extract().as(Comment[].class);

        Assert.assertTrue(comments.length > 0);
        for (Comment comment : comments) {
            Assert.assertEquals(comment.getPostId(), 1);
        }
    }
}


