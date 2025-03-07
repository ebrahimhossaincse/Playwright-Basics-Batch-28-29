package me.ebrahimhossain;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class KeyboardActions {
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
    public void keyboardActions() throws InterruptedException {
        ElementHandle firstName = page.querySelector("#first-name");
        firstName.click();
        Thread.sleep(2000);
        page.keyboard().type("Ebrahim");
        Thread.sleep(1000);
        //Select
        page.keyboard().down("Control");
        page.keyboard().press("KeyA");
        page.keyboard().up("Control");
        Thread.sleep(2000);
        //Copy
        page.keyboard().down("Control");
        page.keyboard().press("KeyC");
        page.keyboard().up("Control");
        Thread.sleep(2000);

        //Tab
        page.keyboard().press("Tab");

        //Paste
        page.keyboard().down("Control");
        page.keyboard().press("KeyV");
        page.keyboard().up("Control");
        Thread.sleep(2000);

    }

    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}
