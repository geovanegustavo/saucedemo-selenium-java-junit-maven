package com.saucedemo.pages;

import com.saucedemo.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object da página de login do SauceDemo.
 * Encapsula os localizadores e ações disponíveis nesta página.
 */
public class LoginPage {

    private WebDriver driver;

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

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // --- AÇÕES NA PÁGINA ---

    /** Abre a página de login no navegador. */
    public void open() {
        driver.get(Constants.BASE_URL);
    }

    /** Limpa e preenche o campo de usuário. */
    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    /** Limpa e preenche o campo de senha. */
    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /** Clica no botão de login. */
    public void clickLogin() {
        loginButton.click();
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
        return errorMessage.getText();
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
