package com.simbirsoft.base;

import com.simbirsoft.pages.AccountPage;
import com.simbirsoft.pages.CustomerLoginPage;
import com.simbirsoft.pages.LoginPage;
import com.simbirsoft.utils.ConfProperties;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTests {

    private WebDriver driver;
    protected LoginPage lp;
    protected CustomerLoginPage cp;
    protected AccountPage ap;
    int timeout = 5;

    @BeforeClass
    public void setUp() {
        DriverManager.initiate();
        driver = DriverManager.getDriverThread();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void goHome() {
        openLoginPage();
    }
    public void openLoginPage() {
        driver.get(ConfProperties.getProperty("homepage"));
        lp = new LoginPage();
        cp = lp.clickCustomerLogin();
        ap = cp.login();
    }

    @AfterMethod
    public void logout() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.quit();
    }
}
