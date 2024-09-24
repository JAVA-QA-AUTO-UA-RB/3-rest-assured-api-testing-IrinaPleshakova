import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.models.Post;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceholderAPIPostsTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void testGetPost() {
        Post post = RestAssured.given(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract().as(Post.class);

        Assert.assertEquals(post.getId(), 1);
        Assert.assertNotNull(post.getTitle());
        Assert.assertNotNull(post.getBody());
    }

    @Test(groups = "regression")
    public void testCreatePost() {
        Post newPost = new Post();
        newPost.setTitle("New Title");
        newPost.setBody("New body content");
        newPost.setUserId(1);

        Post createdPost = RestAssured.given(requestSpec)
                .body(newPost)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract().as(Post.class);

        Assert.assertNotNull(createdPost.getId());
        Assert.assertEquals(createdPost.getTitle(), newPost.getTitle());
        Assert.assertEquals(createdPost.getBody(), newPost.getBody());
    }

    @Test(groups = "regression")
    public void testUpdatePost() {
        Post updatedPost = new Post();
        updatedPost.setId(1);
        updatedPost.setTitle("Updated Title");
        updatedPost.setBody("Updated body content");
        updatedPost.setUserId(1);

        Post responsePost = RestAssured.given(requestSpec)
                .body(updatedPost)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .extract().as(Post.class);

        Assert.assertEquals(responsePost.getTitle(), updatedPost.getTitle());
        Assert.assertEquals(responsePost.getBody(), updatedPost.getBody());
    }

    @Test(groups = "regression")
    public void testDeletePost() {
        RestAssured.given(requestSpec)
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);

        // Повторно запрашиваем ресурс, ожидая, что он все еще доступен, так как это фейковое API
        int statusCode = RestAssured.given(requestSpec)
                .when()
                .get("/posts/1")
                .statusCode();

        // Проверяем, что ресурс все еще существует, так как API не удаляет реальные данные
        Assert.assertEquals(statusCode, 200, "Ресурс все еще доступен, так как API не удаляет данные по-настоящему.");
    }
}


