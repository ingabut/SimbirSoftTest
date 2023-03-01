package com.simbirsoft.pages;

import com.simbirsoft.base.DriverManager;
import com.simbirsoft.utils.CustomTools;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import static com.simbirsoft.utils.CustomTools.systemSleep;
import static com.simbirsoft.utils.CustomTools.writeToFile;

public class AccountPage {

    @FindBy(xpath = "//button[normalize-space(text())='Deposit'][@ng-click='deposit()']")
    private WebElement depositButton;

    @FindBy(xpath = "//button[normalize-space(text())='Withdrawl'][@ng-click='withdrawl()']")
    private WebElement withdrawlButton;

    @FindBy(xpath = "//button[normalize-space(text())='Transactions'][@ng-click='transactions()']")
    private WebElement transactionsButton;

    @FindBy(xpath = "//form[@name='myForm']//input")
    private WebElement amountField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = " //tr[contains(@id, 'anchor')]")
    private List<WebElement> tableElements;

    @FindBy(xpath = "//div[@ng-hide='noAccount'][1]")
    private WebElement balanceDataField;

    @FindBy(xpath = "//label[contains(text(), 'Amount to be Withdrawn')]")
    private WebElement labelWithdrawn;

    @FindBy(xpath = "//span[contains(text(), 'Transaction successful')]")
    private WebElement withdrawnSuccess;

    @FindBy(xpath = "//span[contains(text(), 'Deposit Successful')]")
    private WebElement depositSuccess;

    private static final Logger LOG = LoggerFactory.getLogger(AccountPage.class);

    private static final List<String> transactionList = new ArrayList<>();

    public static final String DEBIT = "Debit";
    public static final String CREDIT = "Credit";
    private static final WebDriverWait wait = DriverManager.getWait();

    public AccountPage() {
        PageFactory.initElements(DriverManager.getDriverThread(), this);
    }
    @Step("Make deposit on {amount}")
    public void makeDeposit(int amount) {
        depositButton.click();
        LOG.info("Нажимаем на кнопку \"Deposit\"");
        amountField.sendKeys(String.valueOf(amount));
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(depositSuccess));
        transactionList.add(String.format("%s\t%s\t%s", formatDateTime(), amount, CREDIT));
    }

    @Step("Make withdrawl on {amount}")
    public void makeWithdraw(int amount) {
        withdrawlButton.click();
        wait.until(ExpectedConditions.visibilityOf(labelWithdrawn));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        amountField.sendKeys(String.valueOf(amount));
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(withdrawnSuccess));
        transactionList.add(String.format("%s\t%s\t%s", formatDateTime(), amount, DEBIT));
    }

    @Step("Check balance")
    public void assertTransactions(int amount) {
        systemSleep(1);
        String balanceData = balanceDataField.getAttribute("textContent");
        String [] balanceArray = balanceData.trim().split(",");
        String balance = Arrays.asList(balanceArray).stream()
                .filter(s -> s.contains("Balance"))
                .findAny()
                .orElse("");
        writeToFile(String.join("\n", transactionList));
        attachTransactions();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(balance.substring(balance.indexOf(":") + 1).trim(),"0","Balance doesn't equal to 0");
        transactionsButton.click();
        List <String> list = tableElements.stream()
                .map(s -> s.getAttribute("textContent"))
                .collect(Collectors.toList());
        Assert.assertTrue(list.size() >= 2, "List of transactions contains less than 2 lines");
        String [] debitData = list.get(list.size() - 1).trim().split("\n");
        String [] creditData = list.get(list.size() - 2).trim().split("\n");
        softAssert.assertTrue(creditData[2].contains(CREDIT));
        softAssert.assertTrue(debitData[2].contains(DEBIT));
        softAssert.assertEquals(Integer.parseInt(creditData[1].trim()), amount,
                                "Amount in the table of transactions doesn't match debited amount");
        softAssert.assertAll();

    }

    @Attachment(value = "transactions.csv", type = "text/csv")
    public static byte[] attachTransactions() {
        try {
            File file = new File(CustomTools.FILE_PATH);
            return CustomTools.toByteArray(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private String formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy hh:mm:ss", new Locale("ru"));
        return LocalDateTime.now().format(formatter);
    }
}
