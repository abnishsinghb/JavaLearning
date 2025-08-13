package com.framework.api.client;

import com.framework.api.model.UserRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public Response getUser(int userId) {
        return given().baseUri(BASE_URL)
                .when().get("/users/" + userId);
    }

    public Response createUser(UserRequest request) {
        return given().baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(request)
                .when().post("/users");
    }
}