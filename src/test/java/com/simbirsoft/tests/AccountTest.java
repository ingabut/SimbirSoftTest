package com.simbirsoft.tests;

import com.simbirsoft.base.BaseTests;
import com.simbirsoft.reporting.TestListener;
import com.simbirsoft.utils.FuncUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.LocalDate;


@Listeners({TestListener.class})
public class AccountTest extends BaseTests {

    @Test(description = "Test deposit and withdraw from account")
    public void testDebitCreditBalance(){
        int fib = FuncUtils.fibonacciCalc(LocalDate.now().getDayOfMonth() + 1);
        ap.makeDeposit(fib);
        ap.makeWithdraw(fib);
        ap.assertTransactions(fib);
    }
}
