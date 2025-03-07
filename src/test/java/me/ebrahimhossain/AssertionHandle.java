package me.ebrahimhossain;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionHandle {
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
        page.navigate("https://testing-and-learning-hub.vercel.app");
        Thread.sleep(5000);
    }

    //@Test(dependsOnMethods = "openUrl")
    public void hardAssertion(){
        String actualTitle = page.title();
        String expectedTitle = "Testing And Learning Hub";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(dependsOnMethods = "openUrl")
    public void softAssertion() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        String actualTitle = page.title();
        String expectedTitle = "Testing And Learning Hub";
        softAssert.assertEquals(actualTitle, expectedTitle);
        Locator web_menu = page.locator("//a[contains(text(),'Web Automation')]");
        web_menu.click();
        Thread.sleep(3000);
        softAssert.assertAll();
    }

    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}
