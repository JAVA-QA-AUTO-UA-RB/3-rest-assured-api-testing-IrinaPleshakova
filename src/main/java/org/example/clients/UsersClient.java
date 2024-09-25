package org.example.clients;

import io.restassured.specification.RequestSpecification;
import org.example.models.User;

public class UsersClient {

	private RequestSpecification requestSpec;

	public UsersClient(RequestSpecification requestSpec) {
		this.requestSpec = requestSpec;
	}

	public User getUserById(int id) {
		return requestSpec
				.when()
				.get("/users/" + id)
				.then()
				.statusCode(200)
				.extract().as(User.class);
	}

	public User[] getUserByUsername(String username) {
		return requestSpec
				.queryParam("username", username)
				.when()
				.get("/users")
				.then()
				.statusCode(200)
				.extract().as(User[].class);
	}
}