																						package com.framework.web.pages;

import com.framework.config.DemoQAConfig;
import com.microsoft.playwright.Page;

/**
 * Page Object for the DemoQA (https://demoqa.com) Book Store login page.
                                * Mirrors the structure of {@link LoginPage}, scoped to the public DemoQA
   * demo site instead of the local login.html fixture.
   */
public class DemoQALoginPage {

	private final Page page;

	// Locators (verified against the live DemoQA DOM)
	private static final String USERNAME_INPUT = "#userName";
  	private static final String PASSWORD_INPUT = "#password";
  	private static final String LOGIN_BUTTON = "#login";
  	private static final String NEW_USER_BUTTON = "#newUser";
  	private static final String ERROR_MESSAGE = "#name";

	public DemoQALoginPage(Page page) {
    		this.page = page;
  }

	public void navigate() {
    		page.navigate(DemoQAConfig.LOGIN_URL);
  }

	public void enterUsername(String username) {
    		page.waitForSelector(USERNAME_INPUT);
    		page.fill(USERNAME_INPUT, username);
  }

	public void enterPassword(String password) {
    		page.waitForSelector(PASSWORD_INPUT);
    		page.fill(PASSWORD_INPUT, password);
  }

	public void clickLogin() {
    		page.waitForSelector(LOGIN_BUTTON);
    		page.click(LOGIN_BUTTON);
  }

	public void login(String username, String password) {
    		enterUsername(username);
    		enterPassword(password);
    		clickLogin();
  }

	/**
	 * Returns true if the "Invalid username or password!" message is visible.
  	 */
	public boolean isErrorMessageDisplayed() {
    		return page.isVisible(ERROR_MESSAGE);
  }

	/**
	 * Waits for and returns the login error message text.
  	 */
	public String getErrorMessage() {
    		page.waitForSelector(ERROR_MESSAGE);
    		return page.textContent(ERROR_MESSAGE);
  }
}
