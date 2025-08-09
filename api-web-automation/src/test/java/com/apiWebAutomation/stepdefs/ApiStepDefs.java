package com.apiWebAutomation.stepdefs;

import io.cucumber.java.en.Given;

public class ApiStepDefs {

    @Given("this is a dummy API step")
    public void this_is_a_dummy_api_step() {
        System.out.println("API step triggered");
    }
}
