package com.simbirsoft.reporting;

import com.simbirsoft.base.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        saveScreenshotPNG(DriverManager.getDriverThread());
    }

}
