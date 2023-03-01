package com.simbirsoft.base;

import com.simbirsoft.utils.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<> ();

    private static WebDriverWait wait;

    public static WebDriver getDriverThread() {
        return driverThread.get();
    }

    public static void setDriverThread(WebDriver driver) {
        driverThread.set(driver);
    }

    public static void initiate() {
        ChromeOptions chromeOptions = new ChromeOptions();
        try {
            RemoteWebDriver driver = new RemoteWebDriver(new URL(ConfProperties.getProperty("gridUrl")), chromeOptions);
            setDriverThread(driver);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public static void quit() {
        getDriverThread().quit();
    }

    public static WebDriverWait getWait() {
        return wait;
    }
}
