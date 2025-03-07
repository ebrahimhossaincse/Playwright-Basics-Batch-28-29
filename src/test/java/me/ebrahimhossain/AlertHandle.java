package me.ebrahimhossain;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AlertHandle {
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
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/javascript_alerts.html");
        Thread.sleep(5000);
    }

    //@Test(dependsOnMethods = "openUrl")
    public void simpleAlert() throws InterruptedException {
        page.onceDialog(dialog -> {
            System.out.println(dialog.message());
            dialog.accept();
        });

        ElementHandle element = page.querySelector("//button[contains(text(),'Show Alert')]");
        element.click();
        Thread.sleep(2000);
    }

    //@Test(dependsOnMethods = "openUrl")
    public void confirmAlert() throws InterruptedException {
        page.onceDialog(dialog -> {
            System.out.println(dialog.type());
            System.out.println(dialog.message());
            dialog.dismiss();
            System.out.println("You clicked Cancel");
        });

        ElementHandle element = page.querySelector("//button[contains(text(),'Show Confirm')]");
        element.click();
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "openUrl")
    public void promptAlert() throws InterruptedException {
        page.onceDialog(dialog -> {
            try {
                System.out.println(dialog.type());
                System.out.println(dialog.message());
                dialog.accept("Hello World");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("You clicked accept");
        });

        ElementHandle element = page.querySelector("//button[contains(text(),'Show Prompt')]");
        element.click();
        Thread.sleep(2000);
    }


    public void alert(String msg) throws InterruptedException {
        String value = "accept";
        page.onceDialog(dialog -> {
            System.out.println(dialog.type());
            System.out.println(dialog.message());
            if(dialog.type().equals("alert")) {
                dialog.accept();
                System.out.println("You clicked accept");
            }else if(dialog.type().equals("confirm")) {
                if(value.equals("accept")) {
                    dialog.accept();
                }else{
                    dialog.dismiss();
                }
            } else if(dialog.type().equals("prompt")) {
                if(value.equals("accept")) {
                    dialog.accept(msg);
                }else{
                    dialog.dismiss();
                }
            }
        });
    }



    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}
