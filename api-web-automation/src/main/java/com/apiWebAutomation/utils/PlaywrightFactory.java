package com.apiWebAutomation.utils;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    public static Playwright initPlaywright() {
        return Playwright.create();
    }

    public static Browser initBrowser(Playwright playwright) {
        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }
}
