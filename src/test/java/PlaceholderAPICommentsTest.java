import org.example.clients.CommentsClient;
import org.example.models.Comment;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaceholderAPICommentsTest extends BaseTest {

    private CommentsClient commentsClient;

    @BeforeClass
    public void setup() {
        commentsClient = new CommentsClient(requestSpec);
    }

    @Test(groups = {"smoke", "regression"})
    public void testGetCommentById() {
        Comment comment = commentsClient.getCommentById(1);

        Assert.assertNotNull(comment.getName());
        Assert.assertEquals(comment.getId(), 1);
        Assert.assertNotNull(comment.getBody());
    }

    @Test(groups = "regression")
    public void testGetCommentsByPostId() {
        Comment[] comments = commentsClient.getCommentsByPostId(1);

        Assert.assertTrue(comments.length > 0);
        for (Comment comment : comments) {
            Assert.assertEquals(comment.getPostId(), 1);
        }
    }
}