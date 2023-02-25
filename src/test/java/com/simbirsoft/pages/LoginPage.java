package com.simbirsoft.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    @FindBy(xpath="//button[normalize-space(text()) = 'Customer Login']")
    private WebElement customerLogin;
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public CustomerLoginPage clickCustomerLogin() {
        customerLogin.click();
        return new CustomerLoginPage(getDriver());
    }
}
