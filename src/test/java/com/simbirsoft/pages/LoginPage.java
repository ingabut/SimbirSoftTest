package com.simbirsoft.pages;

import com.simbirsoft.base.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(xpath="//button[normalize-space(text()) = 'Customer Login']")
    private WebElement customerLogin;
    public LoginPage() {
        PageFactory.initElements(DriverManager.getDriverThread(), this);
    }
    public CustomerLoginPage clickCustomerLogin() {
        customerLogin.click();
        return new CustomerLoginPage();
    }
}
