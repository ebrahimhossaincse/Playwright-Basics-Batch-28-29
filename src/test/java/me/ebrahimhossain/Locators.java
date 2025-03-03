package me.ebrahimhossain;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;


public class Locators {

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

    @Test(priority = 0)
    public void openUrl() throws InterruptedException {
        Thread.sleep(2000);
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/registration_form.html");
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void locateByID() throws InterruptedException {
        ElementHandle first_name = page.querySelector("#first-name"); // By Id
        first_name.fill("Ebrahim");
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void locateByName() throws InterruptedException {
        ElementHandle last_name = page.querySelector("[name='last-name']"); // By Name
        last_name.fill("Hossain");
        Thread.sleep(3000);
    }

    //@Test(priority = 3)
    public void locateByLinkText() throws InterruptedException {
        ElementHandle element = page.querySelector("a:has-text(\"Back\")"); // By Name
        element.click();
        Thread.sleep(3000);
    }

    @Test(priority = 4)
    public void locateByTagName() throws InterruptedException {
        List<ElementHandle> element = page.querySelectorAll("input"); // By Name
        System.out.println("Element Size: " + element.size());
        Thread.sleep(3000);

        for (ElementHandle handle : element) {
            String id = handle.getAttribute("id");
            System.out.println("ID: " + id);
        }
    }

    @Test(priority = 5)
    public void locateByXpath() throws InterruptedException {
        ElementHandle element = page.querySelector("//input[@id='qualification']"); // By Name
        element.fill("SQA");
        Thread.sleep(3000);
    }

    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }

}
