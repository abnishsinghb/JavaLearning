package com.framework.web.stepdefs;

import com.framework.web.pages.LoginPage;
import com.microsoft.playwright.*;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;
    private LoginPage loginPage;

    @Given("User navigates to login page")
    public void user_navigates_to_login_page() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
        loginPage = new LoginPage(page);
        // Point to the login.html file in test resources
        String filePath = "file://" + System.getProperty("user.dir") + "/src/test/resources/login.html";
        loginPage.navigate(filePath);
    }

    @When("User enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("User clicks the login button")
    public void user_clicks_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("Login is successful")
    public void login_is_successful() {
        Assertions.assertTrue(loginPage.isLoginSuccessful());
        page.close();
        browser.close();
        playwright.close();
    }
}