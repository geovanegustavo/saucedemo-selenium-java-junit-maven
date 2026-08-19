package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.Constants;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Login")
public class LoginPageTest extends BaseTest {

    private LoginPage loginPage;

    @Test
    @Story("Login com usuário válido")
    @Severity(SeverityLevel.BLOCKER)
    public void testLoginComUsuarioValido() {
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(Constants.STANDARD_USER, Constants.PASSWORD);

        String currentUrl = loginPage.getCurrentUrl();
        assertTrue(currentUrl.contains(Constants.INVENTORY_URL),
                "URL deveria conter '" + Constants.INVENTORY_URL + "' após login. URL atual: " + currentUrl);
    }

    @Test
    @Story("Login com usuário inválido")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginComUsuarioInvalido() {
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("usuario_invalido", "senha_invalida");

        String errorMessage = loginPage.getErrorMessage();
        assertTrue(errorMessage.contains("Epic sadface: Username and password do not match any user in this service"),
                "Mensagem de erro inesperada: " + errorMessage);
    }
    
}
