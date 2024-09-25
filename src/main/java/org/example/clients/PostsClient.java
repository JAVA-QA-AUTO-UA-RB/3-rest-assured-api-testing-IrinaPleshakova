package org.example.clients;

import io.restassured.specification.RequestSpecification;
import org.example.models.Post;

public class PostsClient {

	private RequestSpecification requestSpec;

	public PostsClient(RequestSpecification requestSpec) {
		this.requestSpec = requestSpec;
	}

	public Post getPostById(int id) {
		return requestSpec
				.when()
				.get("/posts/" + id)
				.then()
				.statusCode(200)
				.extract().as(Post.class);
	}

	public Post createPost(Post newPost) {
		return requestSpec
				.body(newPost)
				.when()
				.post("/posts")
				.then()
				.statusCode(201)
				.extract().as(Post.class);
	}

	public Post updatePost(int id, Post updatedPost) {
		return requestSpec
				.body(updatedPost)
				.when()
				.put("/posts/" + id)
				.then()
				.statusCode(200)
				.extract().as(Post.class);
	}

	public void deletePost(int id) {
		requestSpec
				.when()
				.delete("/posts/" + id)
				.then()
				.statusCode(200);
	}
}