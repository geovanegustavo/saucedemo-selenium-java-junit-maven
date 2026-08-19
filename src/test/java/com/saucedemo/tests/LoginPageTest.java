package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest extends BaseTest {

    private LoginPage loginPage;

    @Test
    public void testLoginComUsuarioValido() {
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(Constants.STANDARD_USER, Constants.PASSWORD);

        String currentUrl = loginPage.getCurrentUrl();
        assertTrue(currentUrl.contains(Constants.INVENTORY_URL),
                "URL deveria conter '" + Constants.INVENTORY_URL + "' após login. URL atual: " + currentUrl);
    }
    
}
