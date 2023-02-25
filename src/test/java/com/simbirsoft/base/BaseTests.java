package com.simbirsoft.base;

import com.simbirsoft.pages.AccountPage;
import com.simbirsoft.pages.CustomerLoginPage;
import com.simbirsoft.pages.LoginPage;
import com.simbirsoft.utils.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTests {

    private WebDriver driver;
    protected LoginPage lp;
    protected CustomerLoginPage cp;
    protected AccountPage ap;
    int timeout = 5;

    public void initiate() {
        ChromeOptions chromeOptions = new ChromeOptions();
        try {
            RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
            DriverManager.setDriverThread(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void setUp() {
        initiate();
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
        lp = new LoginPage(driver);
        cp = lp.clickCustomerLogin();
        ap = cp.login();
    }

    @AfterMethod
    public void logout() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
