package me.ebrahimhossain;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class TableHandling {
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
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/web_table.html");
        Thread.sleep(5000);
    }


    public void fetchTableHeading() throws InterruptedException {
        List<ElementHandle> headers = page.querySelectorAll("//table[@id='table1']/thead/tr/th");
        System.out.println("Table headings: "+headers.size());

        for (ElementHandle header : headers) {
            String value = header.textContent();
            System.out.println("Heading Value: "+value);
        }
    }

    //@Test(dependsOnMethods = "openUrl")
    public void fetchSpecificValue() throws InterruptedException {
        List<ElementHandle> table_rows = page.querySelectorAll("//table[@id='table1']/tbody/tr");

        for(int i=1; i<=table_rows.size(); i++){
            ElementHandle email_locator = page.querySelector("//table[@id='table1']/tbody/tr["+i+"]/td[5]");
            String email = email_locator.textContent();
            System.out.println("Email: "+email);
        }

    }

    @Test(dependsOnMethods = "openUrl")
    public void fetchColValue() throws InterruptedException {
        List<ElementHandle> table_rows = page.querySelectorAll("//table[@id='table1']/tbody/tr");

        for(int i=1; i<=table_rows.size(); i++){
            List<ElementHandle> table_cols = page.querySelectorAll("//table[@id='table1']/tbody/tr[1]/td");
            for(int j=1; j<=table_cols.size(); j++){
                ElementHandle col_locator = page.querySelector("//table[@id='table1']/tbody/tr["+i+"]/td["+j+"]");
                String value = col_locator.textContent();
                System.out.println(value);
            }
            System.out.println("===========================================================");
        }

    }

    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}
