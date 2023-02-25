package com.simbirsoft.pages;

import org.openqa.selenium.WebDriver;

public class AbstractPage {
    private final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}


