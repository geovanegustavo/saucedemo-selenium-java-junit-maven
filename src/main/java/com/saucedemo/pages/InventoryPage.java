package com.saucedemo.pages;

import com.saucedemo.utils.SafeWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object da página de inventário do SauceDemo.
 * Encapsula os localizadores e ações disponíveis nesta página.
 */
public class InventoryPage {

    private SafeWebDriver driver;

    // --- LOCALIZADORES ---

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "title")
    private WebElement pageTitle;

    // --- CONSTRUTOR ---

    public InventoryPage(SafeWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver.getWrappedDriver(), this);
    }

    // --- AÇÕES NA PÁGINA ---

    /** Abre o menu lateral (hamburger) e espera o logout ficar clicável. */
    public void openMenu() {
        driver.safeClick(menuButton);
        driver.waitUntil(org.openqa.selenium.support.ui.ExpectedConditions
                .elementToBeClickable(By.id("logout_sidebar_link")));
    }

    /** Clica no link de logout via JavaScript (contorna overlay do menu animado). */
    public void clickLogout() {
        ((JavascriptExecutor) driver.getWrappedDriver())
                .executeScript("arguments[0].click();", logoutLink);
    }

    /** Realiza o logout completo: abre menu e clica em logout. */
    public void logout() {
        openMenu();
        clickLogout();
    }

    // --- CONSULTAS ---

    /** Retorna o título da página de inventário. */
    public String getPageTitle() {
        return driver.safeGetText(pageTitle);
    }

    /** Retorna a URL atual do navegador. */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
