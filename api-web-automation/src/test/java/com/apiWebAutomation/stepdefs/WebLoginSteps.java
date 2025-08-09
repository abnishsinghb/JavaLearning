package com.apiWebAutomation.stepdefs;

import com.apiWebAutomation.base.BaseTest;
import com.apiWebAutomation.pages.LoginPage;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class WebLoginSteps extends BaseTest {
    private LoginPage loginPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        initBrowser();
        loginPage = new LoginPage(page);
        loginPage.navigateToLoginPage();
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page() {
        Assertions.assertTrue(loginPage.isLoginSuccessful());
        closeBrowser();
    }

    @Then("I should see a login error message")
    public void i_should_see_a_login_error_message() {
        String error = loginPage.getErrorMessage();
        Assertions.assertTrue(error.toLowerCase().contains("epic sadface"));
        closeBrowser();
    }
}
