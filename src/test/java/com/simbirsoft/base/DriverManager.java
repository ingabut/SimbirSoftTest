package com.simbirsoft.base;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<> ();

    public static WebDriver getDriverThread() {
        return driverThread.get();
    }

    public static void setDriverThread(WebDriver driver) {
        driverThread.set(driver);
    }
}
