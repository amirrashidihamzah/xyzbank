package com.example.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    final String accountUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/account";
    //final String transactionUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/listTx";


    public AccountPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Waiting for elements to appear for up to 10 seconds
        this.driver = driver;
    }

    @FindBy(xpath = "button.btn.logout")
    private WebElement logoutBtn;

    @FindBy(css = ".fontBig.ng-binding" )
    private WebElement bigFont;

    @FindBy(xpath = "//div[@class='center']/strong[@class='ng-binding'][1]")
    private WebElement accountNum;

    @FindBy(xpath = "//div[@class='center']/strong[@class='ng-binding'][2]")
    private WebElement balance;

    @FindBy(xpath = "//div[@class='center']/strong[@class='ng-binding'][3]")
    private WebElement currency;

    @FindBy(xpath = " //button[contains(text(),'Transactions')]")
    private WebElement transactionBtn;

    @FindBy(css = "input[placeholder='amount']")
    private WebElement amountInput;
    @FindBy(xpath = "//button[contains(text(),'Deposit')]")
    private WebElement depositBtn;

    @FindBy(css = "button.btn.btn-default")
    private WebElement depositConfirm;

    @FindBy(xpath = "//button[contains(text(),'Withdrawl')]")
    private WebElement withdrawBtn;

    @FindBy(xpath = "//button[text()='Withdraw']")
    private WebElement withdrawConfirm;

    @FindBy(css = "span[ng-show='message']")
    private WebElement message;

    public void logout(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));
        logoutBtn.click();
    }

    public void transact(){
        //driver.get(transactionUrl);
        wait.until(ExpectedConditions.elementToBeClickable(transactionBtn));
        transactionBtn.click();
    }

    public void deposit(){
        //driver.get(accountUrl);
        wait.until(ExpectedConditions.elementToBeClickable(depositBtn));
        depositBtn.click();

        // Convert the integer back to a String to use with sendKeys
        wait.until(ExpectedConditions.visibilityOf(amountInput));
        amountInput.sendKeys("1000");

        wait.until(ExpectedConditions.elementToBeClickable(depositConfirm));
        depositConfirm.click();
    }

    public void withdraw(){
        //driver.get(accountUrl);
        wait.until(ExpectedConditions.elementToBeClickable(withdrawBtn));
        withdrawBtn.click();


        // Convert the integer back to a String to use with sendKeys
        wait.until(ExpectedConditions.visibilityOf(amountInput));
        amountInput.sendKeys("600");

        wait.until(ExpectedConditions.elementToBeClickable(withdrawConfirm));
        withdrawConfirm.click();

    }

    public WebElement getMessage() {
        return message;
    }

    public WebElement getBalance(){
        wait.until(ExpectedConditions.visibilityOf(balance));
        return balance;
    }

    public WebElement getAmountInput(){
        wait.until(ExpectedConditions.visibilityOf(amountInput));
        return  amountInput;
    }
}
