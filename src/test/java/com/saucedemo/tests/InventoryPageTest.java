package com.saucedemo.tests;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.Constants;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Logout")
public class InventoryPageTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Test
    @Story("Logout com sucesso")
    @Severity(SeverityLevel.BLOCKER)
    public void testLogoutComSucesso() {
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(Constants.STANDARD_USER, Constants.PASSWORD);

        inventoryPage = new InventoryPage(driver);
        inventoryPage.logout();

        driver.waitUntil(ExpectedConditions.or(
                ExpectedConditions.urlContains("saucedemo.com"),
                ExpectedConditions.presenceOfElementLocated(
                        org.openqa.selenium.By.id("login-button"))
        ));

        String currentUrl = driver.getCurrentUrl();
        boolean isOnLoginPage = !currentUrl.contains("inventory")
                || driver.safeIsDisplayed(org.openqa.selenium.By.id("login-button"));
        assertTrue(isOnLoginPage,
                "Deveria estar na página de login após logout. URL atual: " + currentUrl);
    }
}
