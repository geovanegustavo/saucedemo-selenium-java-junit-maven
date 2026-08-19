package com.saucedemo.tests;

import com.saucedemo.utils.BrowserFactory;
import com.saucedemo.utils.Constants;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.util.logging.LogManager;

@Epic("SauceDemo")
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeAll
    public static void initLogging() {
        try {
            LogManager.getLogManager().readConfiguration(
                    BaseTest.class.getClassLoader().getResourceAsStream("logging.properties"));
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(BaseTest.class.getName())
                    .warning("Não foi possível carregar logging.properties");
        }
    }

    @BeforeEach
    public void setUp() {
        driver = BrowserFactory.createDriver(Constants.BROWSER);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            Allure.addAttachment("Screenshot final", "image/png",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)),
                    ".png");
            driver.quit();
        }
    }
}
