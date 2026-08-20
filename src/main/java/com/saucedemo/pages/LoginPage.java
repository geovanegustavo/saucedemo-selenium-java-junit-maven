package com.saucedemo.pages;

import com.saucedemo.utils.Constants;
import com.saucedemo.utils.SafeWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object da página de login do SauceDemo.
 * Encapsula os localizadores e ações disponíveis nesta página.
 */
public class LoginPage {

    private SafeWebDriver driver;

    // --- LOCALIZADORES ---

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    // --- CONSTRUTOR ---

    public LoginPage(SafeWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver.getWrappedDriver(), this);
    }

    // --- AÇÕES NA PÁGINA ---

    /** Abre a página de login no navegador. */
    public void open() {
        driver.get(Constants.BASE_URL);
    }

    /** Limpa e preenche o campo de usuário. */
    public void enterUsername(String username) {
        driver.safeType(usernameField, username);
    }

    /** Limpa e preenche o campo de senha. */
    public void enterPassword(String password) {
        driver.safeType(passwordField, password);
    }

    /** Clica no botão de login. */
    public void clickLogin() {
        driver.safeClick(loginButton);
    }

    /** Preenche usuário, senha e clica no botão de login. */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // --- CONSULTAS ---

    /** Retorna o texto da mensagem de erro exibida na página. */
    public String getErrorMessage() {
        return driver.safeGetText(errorMessage);
    }

    /** Retorna a URL atual do navegador. */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /** Retorna o título da página. */
    public String getTitle() {
        return driver.getTitle();
    }
}
