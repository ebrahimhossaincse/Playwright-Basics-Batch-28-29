package me.ebrahimhossain;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Hover {
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
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/menu.html");
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void hoverHandling() throws InterruptedException {
        ElementHandle products = page.querySelector("//a[@id='productsDropdown']");
        products.hover();
        Thread.sleep(5000);

        ElementHandle categories = page.querySelector("//a[contains(text(),'Categories')]");
        categories.hover();
        Thread.sleep(5000);
    }

    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}
