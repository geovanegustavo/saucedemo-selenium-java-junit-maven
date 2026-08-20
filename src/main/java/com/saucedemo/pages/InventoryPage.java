package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object da página de inventário do SauceDemo.
 * Encapsula os localizadores e ações disponíveis nesta página.
 */
public class InventoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // --- LOCALIZADORES ---

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "title")
    private WebElement pageTitle;

    // --- CONSTRUTOR ---

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    // --- AÇÕES NA PÁGINA ---

    /** Abre o menu lateral (hamburger). */
    public void openMenu() {
        menuButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
    }

    /** Clica no link de logout dentro do menu lateral. */
    public void clickLogout() {
        logoutLink.click();
    }

    /** Realiza o logout completo: abre menu e clica em logout. */
    public void logout() {
        openMenu();
        clickLogout();
    }

    // --- CONSULTAS ---

    /** Retorna o título da página de inventário. */
    public String getPageTitle() {
        return pageTitle.getText();
    }

    /** Retorna a URL atual do navegador. */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
