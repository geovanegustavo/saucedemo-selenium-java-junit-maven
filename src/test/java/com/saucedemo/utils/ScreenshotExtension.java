package com.saucedemo.utils;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

public class ScreenshotExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isEmpty()) {
            return;
        }
        SafeWebDriver driver = extractDriver(context.getRequiredTestInstance());
        if (driver != null) {
            Allure.addAttachment("Screenshot falha", "image/png",
                    new ByteArrayInputStream(
                            ((TakesScreenshot) driver.getWrappedDriver())
                                    .getScreenshotAs(OutputType.BYTES)),
                    ".png");
        }
    }

    private SafeWebDriver extractDriver(Object testInstance) {
        Class<?> clazz = testInstance.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField("driver");
                field.setAccessible(true);
                Object value = field.get(testInstance);
                if (value instanceof SafeWebDriver safe) {
                    return safe;
                }
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return null;
    }
}
