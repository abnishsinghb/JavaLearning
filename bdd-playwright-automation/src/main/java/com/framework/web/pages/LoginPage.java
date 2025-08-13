package com.framework.web.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public void enterUsername(String username) {
        page.waitForSelector("#username");
        page.fill("#username", username);
    }

    public void enterPassword(String password) {
        page.waitForSelector("#password");
        page.fill("#password", password);
    }

    public void clickLogin() {
        page.waitForSelector("#loginBtn");
        page.click("#loginBtn");
    }

    public boolean isLoginSuccessful() {
        // This checks for a message "Login successful!" in the #message div
        String message = page.textContent("#message");
        return message != null && message.contains("Login successful");
    }
}