package com.example.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManagerPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ManagerPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Waiting for elements to appear for up to 10 seconds
        this.driver=driver;
    }

    @FindBy(xpath="//button[contains(text(),'Add Customer')]")
    private WebElement addCustomerBtn;

    @FindBy(xpath="//button[contains(text(),'Open Account')]")
    private WebElement openAccountBtn;

    @FindBy(xpath="//button[contains(text(),'Customers')]")
    private WebElement customerListBtn;

    public void addCustomer(){
        wait.until(ExpectedConditions.elementToBeClickable(addCustomerBtn));
        addCustomerBtn.click();
    }

    public void openAccount(){
        wait.until(ExpectedConditions.elementToBeClickable(openAccountBtn));
        openAccountBtn.click();
    }

    public void customerList(){
        wait.until(ExpectedConditions.elementToBeClickable(customerListBtn));
        customerListBtn.click();
    }

}
