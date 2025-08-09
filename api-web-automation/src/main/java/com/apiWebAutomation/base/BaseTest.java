package com.apiWebAutomation.base;

import com.apiWebAutomation.utils.PlaywrightFactory;
import com.microsoft.playwright.*;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext context;
    protected static Page page;

    public void initBrowser() {
        playwright = PlaywrightFactory.initPlaywright();
        browser = PlaywrightFactory.initBrowser(playwright);
        context = browser.newContext();
        page = context.newPage();
    }

    public void closeBrowser() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
