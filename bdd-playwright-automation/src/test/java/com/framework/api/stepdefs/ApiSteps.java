package com.framework.api.stepdefs;

import com.framework.api.client.UserClient;
import com.framework.api.model.UserRequest;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class ApiSteps {
    private final UserClient userClient = new UserClient();
    private Response response;

    @When("I get user with id {int}")
    public void i_get_user_with_id(Integer id) {
        response = userClient.getUser(id);
    }

    @Then("the API response status is {int}")
    public void the_api_response_status_is(Integer status) {
        Assertions.assertEquals(status, response.getStatusCode());
    }

    @And("the API response contains {string}")
    public void the_api_response_contains(String expected) {
        Assertions.assertTrue(response.getBody().asString().contains(expected));
    }

    @When("I create a new user with name {string} username {string} and email {string}")
    public void i_create_a_new_user_with_name_username_and_email(String name, String username, String email) {
        UserRequest req = new UserRequest(name, username, email);
        response = userClient.createUser(req);
    }
}