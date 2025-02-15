package com.example.xyzbank.tests;

import com.example.xyzbank.pages.AccountPage;
import com.example.xyzbank.pages.CustomerPage;
import com.example.xyzbank.pages.TransactionPage;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.example.xyzbank.utils.ScreenshotUtil.takeScreenshot;

public class TransactionTest extends BaseTest {

    private CustomerPage customerPage;
    private AccountPage accountPage;

    private TransactionPage transactionPage;

    public void commonMethodTransaction(){
        customerPage = new CustomerPage(driver);
        accountPage = new AccountPage(driver);
        transactionPage = new TransactionPage(driver);  // Initialize TransactionPage

        customerPage.loginUserWithBalance();  // Log in as a user with balance
        accountPage.transact();
    }

    @Test
    public void latestTransaction() {

        commonMethodTransaction();

        // Get the rows from the transaction table
        List<WebElement> rows = transactionPage.getTransactionRows();

        takeScreenshot(driver,"List of Transaction");

        // Assert that there is at least one row in the transaction table
        Assert.assertFalse(rows.isEmpty(), "Verify that the transaction table has at least one row");
    }

   /* @Test
    public void ResetTransaction(){

        commonMethodTransaction();
        transactionPage.Reset();

        // Get the rows from the transaction table
        WebElement tableBody = transactionPage.getTransactionTableBody();  // Assuming this is the tbody element

        Assert.assertFalse(tableBody.isDisplayed(),"Verify that the transaction table has reset");
    }*/

    @Test
    public void NavigateBack(){

        commonMethodTransaction();
        String initialUrl = driver.getCurrentUrl();
        System.out.println("initialUrl is" + initialUrl);
        String expectedUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/account";

        transactionPage.Back();

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Verify that it goes to correct link with click back .");
    }
}
