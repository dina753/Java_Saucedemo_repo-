package com.practice.saucedemo.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.practice.saucedemo.utils.DriverHelper;

public class TestBase {
    protected WebDriver driver;

    @BeforeMethod
    public void setUpDriver() {
        driver = DriverHelper.createDriver();
    }

    @AfterMethod
    public void tearDownDriver() {
        DriverHelper.quitDriver(driver);
    }
}
