package com.apiWebAutomation.utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ApiUtils {

    public static Response get(String endpoint) {
        return given()
                .relaxedHTTPSValidation()
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response post(String endpoint, String payload) {
        return given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(payload)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response delete(String endpoint) {
        return given()
                .relaxedHTTPSValidation()
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}
