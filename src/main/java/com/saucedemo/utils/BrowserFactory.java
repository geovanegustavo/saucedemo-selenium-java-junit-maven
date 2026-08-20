package com.saucedemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

    public static SafeWebDriver createDriver(BrowserType browserType) {
        WebDriver driver;
        switch (browserType) {
            case CHROME:
                ChromeDriver chrome = new ChromeDriver();
                chrome.manage().window().maximize();
                driver = chrome;
                break;
            case CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                FirefoxDriver firefox = new FirefoxDriver();
                firefox.manage().window().maximize();
                driver = firefox;
                break;
            case FIREFOX_HEADLESS:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case EDGE:
                EdgeDriver edge = new EdgeDriver();
                edge.manage().window().maximize();
                driver = edge;
                break;
            case EDGE_HEADLESS:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless=new");
                driver = new EdgeDriver(edgeOptions);
                break;
            case SAFARI:
                SafariDriver safari = new SafariDriver();
                safari.manage().window().maximize();
                driver = safari;
                break;
            default:
                throw new IllegalArgumentException("Navegador não suportado: " + browserType);
        }
        return new SafeWebDriver(driver);
    }
}
