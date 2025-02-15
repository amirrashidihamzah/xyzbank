package com.example.xyzbank.tests;

import com.example.xyzbank.pages.AccountPage;
import com.example.xyzbank.pages.CustomerPage;
import com.example.xyzbank.pages.HomePage;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.example.xyzbank.utils.ScreenshotUtil.takeScreenshot;

//@Listeners(TestExecutionListener.class)
@Listeners(AllureTestNg.class)
public class AccountTest extends BaseTest {

    private HomePage homePage;
    private CustomerPage customerPage;
    private AccountPage accountPage;

    public void commonCustomerMethod(){
        homePage = new HomePage(driver);
        customerPage = new CustomerPage(driver);
        accountPage = new AccountPage(driver);
        homePage.customerLogin();
    }

    @Test
    public void checkLogoutAndHomeBtn(){
        commonCustomerMethod();
        customerPage.loginUserWithoutBalance(); // Log in before making a deposit
        String initialUrl= "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/account";
        System.out.println("initialUrl is" + initialUrl);

        String expectedUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";

        takeScreenshot(driver,"Before Home ButtonClick");

        //accountPage.logout(); can use any method as it goes to the same link
        homePage.home();

        takeScreenshot(driver,"After Home ButtonClick");

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Verify that the link was correct after click the button.");
    }

    @Test
    public void deposit() {
        commonCustomerMethod();
        customerPage.loginUserWithoutBalance(); // Log in before making a deposit
        accountPage.deposit(); // Perform the deposit action

        WebElement message = accountPage.getMessage();

        takeScreenshot(driver,"Deposit Attempt");

        Assert.assertTrue(message.isDisplayed(), "Verify that the message is displayed.");
        String expectedMessage = "Deposit Successful";
        Assert.assertEquals(message.getText(), expectedMessage, "Verify that the correct message is displayed.");
    }

    @Test
    public void validWithdraw() throws InterruptedException {
        commonCustomerMethod();
        customerPage.loginUserWithBalance();
        accountPage.withdraw();

        WebElement message = accountPage.getMessage();

        takeScreenshot(driver,"Valid Withdraw Attempt");

        Assert.assertTrue(message.isDisplayed(), "Verify that the message is displayed.");
        String expectedMessage = "Transaction successful";
        Assert.assertEquals(message.getText(), expectedMessage, "Verify that the correct message is displayed.");
    }

    @Test
    public void invalidWithdraw() {
        commonCustomerMethod();
        customerPage.loginUserWithoutBalance();
        accountPage.withdraw();

        WebElement message =  getWait().until(ExpectedConditions.visibilityOf(accountPage.getMessage()));

        takeScreenshot(driver,"Invalid Withdraw Attempt");

        String expectedMessage = "Transaction Failed. You can not withdraw amount more than the balance.";
        Assert.assertTrue(message.isDisplayed(), "Verify that the message is displayed.");
        Assert.assertEquals(message.getText(), expectedMessage, "Verify that the correct message is displayed.");
    }
}
