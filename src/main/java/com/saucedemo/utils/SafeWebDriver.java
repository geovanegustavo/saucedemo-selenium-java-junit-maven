package com.saucedemo.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * Wrapper do WebDriver com auto-waiting automático.
 * Aguarda elementos estarem visíveis, habilitados e clicáveis
 * antes de executar ações — similar ao comportamento do Playwright.
 */
public class SafeWebDriver implements WebDriver {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration POLLING = Duration.ofMillis(500);

    public SafeWebDriver(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT, POLLING);
    }

    public SafeWebDriver(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout, POLLING);
    }

    // ==================== AÇÕES COM AUTO-WAIT ====================

    /**
     * Clica em um elemento após garantir que está visível, habilitado e clicável.
     */
    public void safeClick(By by) {
        waitAndClick(driver.findElement(by));
    }

    /**
     * Clica em um WebElement após garantir que está visível, habilitado e clicável.
     */
    public void safeClick(WebElement element) {
        waitAndClick(element);
    }

    /**
     * Preenche um campo de texto após garantir que está habilitado.
     * Limpa o campo antes de digitar.
     */
    public void safeType(By by, String text) {
        WebElement element = waitUntilVisible(by);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Preenche um WebElement após garantir que está habilitado.
     */
    public void safeType(WebElement element, String text) {
        waitUntilVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Retorna o texto de um elemento após garantir que está visível.
     */
    public String safeGetText(By by) {
        return waitUntilVisible(by).getText();
    }

    /**
     * Retorna o texto de um WebElement após garantir que está visível.
     */
    public String safeGetText(WebElement element) {
        waitUntilVisible(element);
        return element.getText();
    }

    /**
     * Retorna o valor de um atributo após garantir que o elemento existe.
     */
    public String safeGetAttribute(By by, String attribute) {
        return waitUntilPresent(by).getDomAttribute(attribute);
    }

    /**
     * Verifica se um elemento está visível.
     */
    public boolean safeIsDisplayed(By by) {
        try {
            return waitUntilVisible(by).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Verifica se um elemento está habilitado.
     */
    public boolean safeIsEnabled(By by) {
        try {
            return waitUntilVisible(by).isEnabled();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Espera até que um elemento desapareça da página.
     */
    public void waitUntilHidden(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Espera até que a URL contenha o texto especificado.
     */
    public void waitUntilUrlContains(String fraction) {
        wait.until(ExpectedConditions.urlContains(fraction));
    }

    /**
     * Espera até que a URL seja exatamente a especificada.
     */
    public void waitUntilUrlIs(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    /**
     * Espera uma condição customizada.
     */
    public <T> T waitUntil(ExpectedCondition<T> condition) {
        return wait.until(condition);
    }

    // ==================== MÉTODOS INTERNOS ====================

    private void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    private WebElement waitUntilVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private WebElement waitUntilPresent(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    // ==================== DELEGADOS (WebDriver) ====================

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    /**
     * Retorna o driver original (para casos que precisam do driver real).
     */
    public WebDriver getWrappedDriver() {
        return driver;
    }
}
