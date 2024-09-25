import org.example.clients.PostsClient;
import org.example.models.Post;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaceholderAPIPostsTest extends BaseTest {

    private PostsClient postsClient;

    @BeforeClass
    public void setup() {
        postsClient = new PostsClient(requestSpec);
    }

    @Test(groups = {"smoke", "regression"})
    public void testGetPost() {
        Post post = postsClient.getPostById(1);

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

        Post createdPost = postsClient.createPost(newPost);

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

        Post responsePost = postsClient.updatePost(1, updatedPost);

        Assert.assertEquals(responsePost.getTitle(), updatedPost.getTitle());
        Assert.assertEquals(responsePost.getBody(), updatedPost.getBody());
    }

    @Test(groups = "regression")
    public void testDeletePost() {
        postsClient.deletePost(1);

        // Since JSONPlaceholder doesn't actually delete data, we don't check for resource unavailability
        // but we can check that the DELETE request returned status 200
        Post post = postsClient.getPostById(1);
        Assert.assertNotNull(post, "The resource is still available, because the API doesn't actually delete the data.");
    }
}