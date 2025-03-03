package me.ebrahimhossain;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class GetCommands {
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

    @Test(dependsOnMethods = "openUrl")
    public void fetch() throws InterruptedException {
        System.out.println("Title: "+ page.title());
        System.out.println("Url: "+ page.url());
        //System.out.println("Page Source: "+page.content());
    }

    @Test(dependsOnMethods = "openUrl")
    public void getCssValue() throws InterruptedException {
        ElementHandle element = page.querySelector("//body/main[@id='courses']/div[1]/div[2]/div[1]/div[1]/a[1]");
        String color = element.evaluate("element => getComputedStyle(element).backgroundColor").toString();
        System.out.println(color);
    }

    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}
