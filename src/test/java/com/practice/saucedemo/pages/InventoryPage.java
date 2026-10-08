package com.practice.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practice.saucedemo.utils.BrowserUtils;
import com.practice.saucedemo.utils.ConfigReader;

import java.time.Duration;

public class InventoryPage {
    private final WebDriver driver;
    private final BrowserUtils browserUtils;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.browserUtils = new BrowserUtils(driver,
                Duration.ofSeconds(ConfigReader.getIntProperty("explicit.wait.seconds")));
    }

    public String getTitle() {
        return browserUtils.waitForVisibility(By.cssSelector("[data-test='title']")).getText();
    }

    public boolean isOpen() {
        return browserUtils.waitForUrlContains("/inventory.html");
    }
}
