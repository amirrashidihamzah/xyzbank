package com.example.xyzbank.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        // Take screenshot and store it as a file
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Specify the destination path where the screenshot will be saved
        File destination = new File("./screenshots/" + screenshotName + ".png");

        try {
            // Copy the screenshot to the destination
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot taken and saved at: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
