package com.saucedemo.tests;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.Constants;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

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

        String currentUrl = inventoryPage.getCurrentUrl();
        assertTrue(currentUrl.equals(Constants.BASE_URL),
                "URL deveria ser '" + Constants.BASE_URL + "' após logout. URL atual: " + currentUrl);
    }
}
