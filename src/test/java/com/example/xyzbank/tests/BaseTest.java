package com.example.xyzbank.tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;


public class BaseTest {

    private String baseUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";

    protected static WebDriver driver;  // Made protected so that it's accessible in subclasses

    private WebDriverWait wait;  // Initialize WebDriverWait as well

    @Step("Start the application")
    @BeforeMethod
    @Parameters("browser") // Accepts the browser parameter from testng.xml
    public void setUp(String browser) {
        // Initialize the driver
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }

        // Initialize WebDriverWait with the driver
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the login page before each test
        driver.get(baseUrl);

        driver.manage().window().maximize();
    }

    @Step("Stop the application")
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriverWait getWait() {
        return wait;
    }
}