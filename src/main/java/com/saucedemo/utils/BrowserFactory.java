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

    public static WebDriver createDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                ChromeDriver chrome = new ChromeDriver();
                chrome.manage().window().maximize();
                return chrome;
            case CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                FirefoxDriver firefox = new FirefoxDriver();
                firefox.manage().window().maximize();
                return firefox;
            case FIREFOX_HEADLESS:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);
            case EDGE:
                EdgeDriver edge = new EdgeDriver();
                edge.manage().window().maximize();
                return edge;
            case EDGE_HEADLESS:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless=new");
                return new EdgeDriver(edgeOptions);
            case SAFARI:
                SafariDriver safari = new SafariDriver();
                safari.manage().window().maximize();
                return safari;
            default:
                throw new IllegalArgumentException("Navegador não suportado: " + browserType);
        }
    }
}
