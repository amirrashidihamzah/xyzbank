package com.example.xyzbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerListPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CustomerListPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver=driver;
    }

    @FindBy(css = "input.form-control[placeholder='Search Customer']")
    private WebElement searchInput;

    @FindBy(xpath = "(//table//tbody//tr)[last()]//button[text()='Delete']")
    private WebElement deleteLastBtn;

    @FindBy(css = "tr.ng-scope")
    private WebElement tableData;

    @FindBy(xpath = "//td[contains(text(), 'Albus')]")
    private WebElement tableResult;

    public void searchCustomer(){
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.sendKeys("albus");
        wait.until(ExpectedConditions.visibilityOf(tableData));
    }

    public WebElement getTableResult(){
        wait.until(ExpectedConditions.visibilityOf(tableResult));
        return  tableResult;
    }
}
