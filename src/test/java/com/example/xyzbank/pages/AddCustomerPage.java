package com.example.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddCustomerPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor for the page object
    public AddCustomerPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Waiting for elements to appear for up to 10 seconds
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[ng-model='fName']")
    private WebElement firstNameInput;

    @FindBy(css = "input[ng-model='lName']")
    private WebElement lastNameInput;

    @FindBy(css = "input[ng-model='postCd']")
    private WebElement postcodeInput;

    @FindBy(xpath = "//button[contains(text(),'Add Customer') and contains (@class,'btn-default')]")
    private WebElement addCustomerBtn;

    public void addCustomer(String firstName, String lastName, String postcode){
        // Wait for the elements to be visible
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.sendKeys(firstName);

        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        lastNameInput.sendKeys(lastName);

        wait.until(ExpectedConditions.visibilityOf(postcodeInput));
        postcodeInput.sendKeys(postcode);

        // Click the Add Customer button
        wait.until(ExpectedConditions.elementToBeClickable(addCustomerBtn));
        addCustomerBtn.click();
    }
}
