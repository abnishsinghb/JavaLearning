package com.framework.web.stepdefs;

import com.framework.web.pages.DemoQALoginPage;
import com.microsoft.playwright.*;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

/**
 * Step definitions for the DemoQA Book Store login scenario. Mirrors the
   * structure of {@link LoginSteps}, but drives the public demoqa.com site
   * instead of the local login.html fixture.
   */
public class DemoQALoginSteps {
  	private static Playwright playwright;
  	private static Browser browser;
  	private static Page page;
  	private DemoQALoginPage demoQALoginPage;

	@Given("User navigates to the DemoQA login page")
  	public void user_navigates_to_the_demoqa_login_page() {
      		playwright = Playwright.create();
      		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
      		page = browser.newPage();
      		demoQALoginPage = new DemoQALoginPage(page);
      		demoQALoginPage.navigate();
    }

	@When("User enters DemoQA username {string} and password {string}")
  	public void user_enters_demoqa_username_and_password(String username, String password) {
      		demoQALoginPage.enterUsername(username);
      		demoQALoginPage.enterPassword(password);
    }

	@When("User clicks the DemoQA login button")
  	public void user_clicks_the_demoqa_login_button() {
      		demoQALoginPage.clickLogin();
    }

	@Then("User should see the DemoQA error message {string}")
  	public void user_should_see_the_demoqa_error_message(String expectedMessage) {
      		Assertions.assertEquals(expectedMessage, demoQALoginPage.getErrorMessage());
      		page.close();
      		browser.close();
      		playwright.close();
    }
}
