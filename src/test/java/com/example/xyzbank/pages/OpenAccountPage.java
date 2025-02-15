package com.example.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenAccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public OpenAccountPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Waiting for elements to appear for up to 10 seconds
        this.driver=driver;
    }

    @FindBy(xpath = "//select[@id='userSelect']")
    private WebElement selectedUser;

    @FindBy(xpath = "//select[@id='currency']")
    private WebElement selectedCurrency;

    @FindBy(xpath = "//button[text()='Process']")
    private WebElement processBtn;

    public void process(){
        wait.until(ExpectedConditions.elementToBeClickable(processBtn));
        processBtn.click();
    }

    public void selectCustomer(){
        wait.until(ExpectedConditions.visibilityOf(selectedUser));
        Select userSelect = new Select(selectedUser);
        userSelect.selectByVisibleText("Hermoine Granger");  // Select user with balance
    }

    public void selectCurrency(){
        wait.until(ExpectedConditions.visibilityOf(selectedCurrency));
        Select currencySelect = new Select(selectedCurrency);
        currencySelect.selectByVisibleText("Dollar");
    }
}
