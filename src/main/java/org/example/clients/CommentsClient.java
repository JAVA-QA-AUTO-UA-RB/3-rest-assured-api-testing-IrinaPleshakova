package org.example.clients;

import io.restassured.specification.RequestSpecification;
import org.example.models.Comment;

public class CommentsClient {

	private RequestSpecification requestSpec;

	public CommentsClient(RequestSpecification requestSpec) {
		this.requestSpec = requestSpec;
	}

	public Comment getCommentById(int id) {
		return requestSpec
				.when()
				.get("/comments/" + id)
				.then()
				.statusCode(200)
				.extract().as(Comment.class);
	}

	public Comment[] getCommentsByPostId(int postId) {
		return requestSpec
				.queryParam("postId", postId)
				.when()
				.get("/comments")
				.then()
				.statusCode(200)
				.extract().as(Comment[].class);
	}
}