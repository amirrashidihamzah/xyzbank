package com.example.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

  private WebDriver driver;
  private WebDriverWait wait;

  public HomePage(WebDriver driver){
      PageFactory.initElements(driver, this);
      this.driver = driver;
      this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Waiting for elements to appear for up to 10 seconds
  }

  @FindBy(css = "button.btn.home")
  private WebElement homeButton;

  @FindBy(css = ".mainHeading")
  private WebElement bankName;

  @FindBy(xpath = "//button[text()='Customer Login']")
  private WebElement customerLoginBtn;

  @FindBy(xpath = "//button[text()='Bank Manager Login']")
  private WebElement managerLoginBtn;

 public void home(){
   wait.until(ExpectedConditions.elementToBeClickable(homeButton));
   homeButton.click();
 }

  public void customerLogin(){
     wait.until(ExpectedConditions.elementToBeClickable(customerLoginBtn));
     customerLoginBtn.click();
  }

  public  void managerLogin(){
      wait.until(ExpectedConditions.elementToBeClickable(managerLoginBtn));
      managerLoginBtn.click();
  }
}
