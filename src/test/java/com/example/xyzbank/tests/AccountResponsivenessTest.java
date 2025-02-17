package com.example.xyzbank.tests;

import com.example.xyzbank.pages.AccountPage;
import com.example.xyzbank.pages.CustomerPage;
import com.example.xyzbank.pages.HomePage;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import static com.example.xyzbank.utils.ScreenshotUtil.takeScreenshot;
import static org.testng.Assert.assertTrue;

public class AccountResponsivenessTest extends BaseTest{

    private HomePage homePage;
    private CustomerPage customerPage;
    private AccountPage accountPage;

    @Test
    public void testResponsiveLayout() {
        // Common setup
        homePage = new HomePage(driver);
        customerPage = new CustomerPage(driver);
        accountPage = new AccountPage(driver);

        // Array of different viewports (width x height)
        int[][] viewports = {
                {1920, 1080},  // Full HD (Desktop)
                {1366, 768},   // Smaller desktop
                {1024, 768},   // Tablet Landscape
                {768, 1024},   // Tablet Portrait
                {375, 667},    // iPhone 6 Portrait
                {667, 375},    // iPhone 6 Landscape
                {412, 915},    // Android Phone Portrait
                {915, 412},    // Android Phone Landscape
                {360, 640},    // Small Android Phone
                {1440, 2560}   // Large tablet or small laptop
        };

        // Perform tests similar to your functional tests, e.g., verify elements are present
        homePage.customerLogin();
        customerPage.loginUserWithoutBalance(); // Log in before making a deposit

        // Loop through viewports and test layout
        for (int[] viewport : viewports) {
            int width = viewport[0];
            int height = viewport[1];

            driver.manage().window().setSize(new Dimension(width, height));
            System.out.println("Testing responsive layout on: " + width + "x" + height);

            // You can take a screenshot for each viewport (optional)
            takeScreenshot(driver, "Viewport-" + width + "x" + height);

            // Perform some assertions to verify elements are correctly rendered (optional)
            assertTrue(accountPage.getDepositBtn().isDisplayed(), "Verify deposit button visibility on " + width + "x" + height);
        }
    }
}
