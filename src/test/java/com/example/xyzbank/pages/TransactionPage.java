package com.example.xyzbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TransactionPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public TransactionPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Waiting for elements to appear for up to 10 seconds
        this.driver=driver;
    }

    @FindBy(xpath = "//button[text()='Back']")
    private WebElement backBtn;

    @FindBy(xpath = "//button[text()='Reset']")
    private WebElement resetBtn;

    @FindBy(css = "Table.table.table-bordered.table-striped")
    private WebElement transactionTable;

    @FindBy(id="tr.ng-scope")
    private WebElement tableContent;

    public void Back(){
        wait.until(ExpectedConditions.elementToBeClickable(backBtn));
        backBtn.click();
    }

    public void Reset(){
        wait.until(ExpectedConditions.elementToBeClickable(resetBtn));
        resetBtn.click();
    }

    public WebElement getTableContent(){
        wait.until(ExpectedConditions.invisibilityOf(tableContent));
        return tableContent;
    }

    // Method to return the rows of the transaction table
    public List<WebElement> getTransactionRows() {
        // Wait for the table to be visible
        wait.until(ExpectedConditions.visibilityOf(transactionTable));
        // Return all rows in the table body (excluding the header row)
        return transactionTable.findElements(By.cssSelector("tbody tr"));
    }

    public WebElement getTransactionTableBody() {
        // Locate the tbody within the transactionTable
        return transactionTable.findElement(By.tagName("tbody"));
    }

    // Method to extract data from each column of a given row
    public List<String> getTransactionRowData(WebElement row) {
        List<String> rowData = new ArrayList<>();
        // Locate columns in the row (Date-Time, Amount, Transaction Type)
        rowData.add(row.findElement(By.xpath("./td[1]")).getText());  // Date-Time
        rowData.add(row.findElement(By.xpath("./td[2]")).getText());  // Amount
        rowData.add(row.findElement(By.xpath("./td[3]")).getText());  // Transaction Type
        return rowData;
    }
}
