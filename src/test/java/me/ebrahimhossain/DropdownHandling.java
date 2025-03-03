package me.ebrahimhossain;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DropdownHandling {
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
    public void selectByIndex() throws InterruptedException {
        ElementHandle genders = page.querySelector("#gender");
        genders.selectOption(new SelectOption().setIndex(2));
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void selectByValue() throws InterruptedException {
        ElementHandle genders = page.querySelector("#gender");
        genders.selectOption(new SelectOption().setValue("male"));
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void selectByVisibleText() throws InterruptedException {
        ElementHandle genders = page.querySelector("#gender");
        genders.selectOption(new SelectOption().setLabel("Other"));
        Thread.sleep(5000);
    }

    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}
