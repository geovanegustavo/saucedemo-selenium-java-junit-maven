package com.saucedemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    public static SafeWebDriver createDriver(BrowserType browserType) {
        WebDriver driver;
        switch (browserType) {
            case CHROME:
                ChromeDriver chrome = new ChromeDriver(configureChrome());
                chrome.manage().window().maximize();
                driver = chrome;
                break;
            case CHROME_HEADLESS:
                ChromeOptions chromeHeadless = configureChrome();
                chromeHeadless.addArguments("--headless=new");
                driver = new ChromeDriver(chromeHeadless);
                break;
            case FIREFOX:
                FirefoxDriver firefox = new FirefoxDriver(configureFirefox());
                firefox.manage().window().maximize();
                driver = firefox;
                break;
            case FIREFOX_HEADLESS:
                FirefoxOptions firefoxHeadless = configureFirefox();
                firefoxHeadless.addArguments("--headless");
                driver = new FirefoxDriver(firefoxHeadless);
                break;
            case EDGE:
                EdgeDriver edge = new EdgeDriver(configureEdge());
                edge.manage().window().maximize();
                driver = edge;
                break;
            case EDGE_HEADLESS:
                EdgeOptions edgeHeadless = configureEdge();
                edgeHeadless.addArguments("--headless=new");
                driver = new EdgeDriver(edgeHeadless);
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

    private static ChromeOptions configureChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-extensions",
                "--disable-infobars",
                "--disable-notifications",
                "--disable-popup-blocking",
                "--disable-save-password-bubble",
                "--no-first-run",
                "--no-default-browser-check",
                "--disable-component-update",
                "--password-store=basic"
        );
        options.setExperimentalOption("excludeSwitches",
                new String[]{"enable-automation", "enable-logging"});
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.default_content_setting_values.notifications", 2
        ));
        return options;
    }

    private static FirefoxOptions configureFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("signon.rememberSignons", false);
        prefs.put("signon.autofillForms", false);
        prefs.put("dom.disable_open_during_load", true);
        prefs.put("permissions.default.desktop-notification", 2);
        prefs.put("datareporting.policy.dataSubmissionEnabled", false);
        prefs.put("extensions.autoDisableScopes", 15);
        prefs.put("extensions.enabledScopes", 0);
        options.addPreference("signon.rememberSignons", false);
        options.addPreference("signon.autofillForms", false);
        options.addPreference("dom.disable_open_during_load", true);
        options.addPreference("permissions.default.desktop-notification", 2);
        options.addPreference("datareporting.policy.dataSubmissionEnabled", false);
        options.addPreference("extensions.autoDisableScopes", 15);
        options.addPreference("extensions.enabledScopes", 0);
        return options;
    }

    private static EdgeOptions configureEdge() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments(
                "--disable-extensions",
                "--disable-infobars",
                "--disable-notifications",
                "--disable-popup-blocking",
                "--disable-save-password-bubble",
                "--no-first-run",
                "--no-default-browser-check",
                "--disable-component-update",
                "--password-store=basic"
        );
        options.setExperimentalOption("excludeSwitches",
                new String[]{"enable-automation", "enable-logging"});
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.default_content_setting_values.notifications", 2
        ));
        return options;
    }
}
