package com.simbirsoft.pages;

import com.simbirsoft.utils.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class CustomerLoginPage extends AbstractPage {
    private final String name = ConfProperties.getProperty("login");

    @FindBy(id = "userSelect")
    private WebElement selectField;

    @FindBy(xpath = "//option[@class='ng-binding ng-scope']")
    private List<WebElement> loginList;

    @FindBy(xpath = "//button[text() = 'Login']")
    private WebElement loginButton;
    public CustomerLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public AccountPage login() {
        WebElement loginElement = loginList.stream()
                .filter(el -> el.getText().contains(name))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
        loginElement.click();
        loginButton.click();

        return new AccountPage(getDriver());
    }
}
