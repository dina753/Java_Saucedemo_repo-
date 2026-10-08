package com.practice.saucedemo.utils;

import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.manager.SeleniumManagerOutput.Result;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;

public final class DriverHelper {
    private DriverHelper() {
    }

    public static WebDriver createDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        String driverProperty = switch (browser) {
            case "chrome" -> "webdriver.chrome.driver";
            case "firefox" -> "webdriver.gecko.driver";
            case "edge" -> "webdriver.edge.driver";
            default -> throw new IllegalArgumentException(
                    "Unsupported browser in configuration: " + browser);
        };
        String driverPath = resolveDriverPath(browser);
        String previousDriverPath = System.getProperty(driverProperty);
        WebDriver driver;
        try {
            System.setProperty(driverProperty, driverPath);
            driver = switch (browser) {
                case "chrome" -> new ChromeDriver();
                case "firefox" -> new FirefoxDriver();
                case "edge" -> new EdgeDriver();
                default -> throw new IllegalArgumentException(
                        "Unsupported browser in configuration: " + browser);
            };
        } finally {
            if (previousDriverPath == null) {
                System.clearProperty(driverProperty);
            } else {
                System.setProperty(driverProperty, previousDriverPath);
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        return driver;
    }

    private static String resolveDriverPath(String browser) {
        Result result = SeleniumManager.getInstance()
                .getBinaryPaths(List.of("--browser", browser, "--skip-driver-in-path"));
        if (result.getCode() != 0 || result.getDriverPath().isBlank()) {
            throw new IllegalStateException(
                    "Could not resolve a compatible " + browser + " driver: " + result.getMessage());
        }
        return result.getDriverPath();
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            // driver.quit();
        }
    }
}
