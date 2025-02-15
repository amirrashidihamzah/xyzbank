package com.example.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerPage {

    private WebDriver driver;
    private WebDriverWait wait;

    final String customerLoginUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer";

    private HomePage homePage;
    // Constructor to initialize elements and WebDriverWait
    public CustomerPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Waiting for elements to appear for up to 10 seconds
    }

    // Page elements for user selection and login
    @FindBy(id = "userSelect")
    private WebElement selectedUser;

    @FindBy(css = "button.btn.btn-default")
    private WebElement loginBtn;

    // Select user by index
    public void selectUserByIndex(int index){
        Select userLogin = new Select(selectedUser);
        int optionsCount = userLogin.getOptions().size();
        if (index >= 0 && index < optionsCount) {
            userLogin.selectByIndex(index); // Select user by index
        }
    }

    // Select a user without balance
    public void loginUserWithoutBalance(){
        navigateToLoginPage(); // Navigate to the customer login URL
        selectUserByIndex(getNumberOfUsers() - 1); // Select the last user (without balance)
        clickLoginButton();
    }

    // Select a user with balance
    public void loginUserWithBalance(){
        navigateToLoginPage();  // Navigate to the customer login URL
        selectUserByVisibleText("Hermoine Granger");  // Select user with balance
        clickLoginButton();  // Click login button
    }

    // Generalized method to navigate to the login page
    private void navigateToLoginPage(){
        driver.get(customerLoginUrl); // Navigate to the customer login URL
        wait.until(ExpectedConditions.visibilityOf(selectedUser)); // Wait until the dropdown is visible
    }

    // Generalized method to select user by visible text
    public void selectUserByVisibleText(String userName){
        Select userLogin = new Select(selectedUser);
        userLogin.selectByVisibleText(userName);
    }

    // Generalized method to click the login button
    private void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)); // Wait until the button is clickable
        loginBtn.click(); // Click the login button
    }

    // Get the number of users available in the dropdown (useful for selecting the last user)
    private int getNumberOfUsers() {
        Select userLogin = new Select(selectedUser);
        return userLogin.getOptions().size(); // Return the number of users
    }
}
