package com.apiWebAutomation.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    // Locators
    private final String usernameInput = "#user-name";
    private final String passwordInput = "#password";
    private final String loginBtn = "#login-button";
    private final String errorMsg = "[data-test='error']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToLoginPage() {
        page.navigate("https://www.saucedemo.com/");
    }

    public void enterUsername(String username) {
        page.fill(usernameInput, username);
    }

    public void enterPassword(String password) {
        page.fill(passwordInput, password);
    }

    public void clickLogin() {
        page.click(loginBtn);
    }

    public String getErrorMessage() {
        return page.textContent(errorMsg);
    }

    public boolean isLoginSuccessful() {
        return page.url().contains("inventory.html");
    }
}
