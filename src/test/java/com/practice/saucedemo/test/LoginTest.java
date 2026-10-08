package com.practice.saucedemo.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.practice.saucedemo.pages.InventoryPage;
import com.practice.saucedemo.pages.LoginPage;
import com.practice.saucedemo.utils.ConfigReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends TestBase {
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @Test
    public void standardUserCanLogIn() {
        InventoryPage inventoryPage = loginPage.login(
                ConfigReader.getProperty("login.username"),
                ConfigReader.getProperty("login.password"));

        assertEquals(inventoryPage.getTitle(), "Products");
        assertTrue(inventoryPage.isOpen());
    }
}
