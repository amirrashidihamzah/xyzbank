package com.example.xyzbank.tests;

import com.example.xyzbank.pages.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.xyzbank.utils.ScreenshotUtil.takeScreenshot;

public class ManagerFunctionalityTest extends BaseTest {

   private HomePage homePage;
   private ManagerPage managerPage;
   private AddCustomerPage addCustomerPage;
   private OpenAccountPage openAccountPage;
   private CustomerListPage customerListPage;

   public void commonManagerMethod(){
      homePage = new HomePage(driver);
      managerPage = new ManagerPage(driver);
      homePage.managerLogin();
   }

   @Test
   public void insertExistingCustomer(){
      commonManagerMethod();
      addCustomerPage = new AddCustomerPage(driver);
      managerPage.addCustomer();
      addCustomerPage.addCustomer("Hermoine","Granger","E859AB");

      Alert alert= driver.switchTo().alert();

      String alertText = alert.getText();
      Assert.assertEquals("Please check the details. Customer may be duplicate.", alertText);
      alert.dismiss();
   }

   @Test
   public void openAccount(){
      commonManagerMethod();
      openAccountPage = new OpenAccountPage(driver);
      managerPage.openAccount();
      openAccountPage.selectCustomer();
      openAccountPage.selectCurrency();
      openAccountPage.process();

       Alert alert= driver.switchTo().alert();
       String alertText = alert.getText();

       // Define a regex pattern to match the expected message with any account number
       String expectedPattern = "Account created successfully with account Number :\\d+";
       Pattern pattern = Pattern.compile(expectedPattern);

       // Match the alert text with the pattern
       Matcher matcher = pattern.matcher(alertText);

      // Assert that the alert text matches the expected pattern
       Assert.assertTrue(matcher.matches(), "Alert text does not match the expected format.");

       alert.dismiss();
   }

   @Test
   public void searchCustomer(){
       commonManagerMethod();
       customerListPage = new CustomerListPage(driver);
       managerPage.customerList();
       customerListPage.searchCustomer();
       WebElement customerData = customerListPage.getTableResult();

       takeScreenshot(driver,"Search Result Successful");

       Assert.assertTrue(customerData.isDisplayed(),"Customer 'Albus' not found in the table");
   }
}
