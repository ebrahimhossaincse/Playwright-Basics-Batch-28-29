package me.ebrahimhossain;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class NavigationCommands {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeSuite
    public void start(){
        playwright = Playwright.create();
        browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browser.newPage();
        System.out.println("Browser version: " + browser.version());
    }

    @Test
    public void openUrl() throws InterruptedException {
        Thread.sleep(2000);
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/registration_form.html");
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void navigations() throws InterruptedException {
        ElementHandle home = page.querySelector("//header/div[1]/nav[1]/a[1]");
        home.click();
        Thread.sleep(2000);

        //Back
        page.goBack();
        Thread.sleep(2000);
        //Refresh
        page.reload();
        Thread.sleep(2000);

        //Forward
        page.goForward();
        Thread.sleep(2000);

    }

    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}
