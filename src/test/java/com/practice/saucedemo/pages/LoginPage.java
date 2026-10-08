package com.practice.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practice.saucedemo.utils.BrowserUtils;
import com.practice.saucedemo.utils.ConfigReader;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final BrowserUtils browserUtils;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.browserUtils = new BrowserUtils(driver,
                Duration.ofSeconds(ConfigReader.getIntProperty("explicit.wait.seconds")));
    }

    public void open() {
        driver.get(ConfigReader.getProperty("pages.login.url"));
    }

    public InventoryPage login(String username, String password) {
        browserUtils.waitForVisibility(By.id("user-name")).sendKeys(username);
        browserUtils.waitForVisibility(By.id("password")).sendKeys(password);
        browserUtils.waitForVisibility(By.id("login-button")).click();
        return new InventoryPage(driver);
    }
}
