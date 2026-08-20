package com.saucedemo.utils;

public class Constants {

    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String STANDARD_USER = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    public static final String INVENTORY_URL = "/inventory.html";

    public static final BrowserType BROWSER = BrowserType.valueOf(
            System.getProperty("browser", BrowserType.CHROME_HEADLESS.name())
    );

}
