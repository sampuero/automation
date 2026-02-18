package com.portafolio.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener extends TestListenerAdapter {
    
    private WebDriver driver;
    
    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        if (currentClass instanceof com.portafolio.automation.base.BaseTest) {
            driver = ((com.portafolio.automation.base.BaseTest) currentClass).getDriver();
            
            if (driver != null) {
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = "screenshot_" + result.getName() + "_" + timestamp + ".png";
                
                try {
                    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    Path destination = Paths.get("test-output/screenshots/" + fileName);
                    Files.createDirectories(destination.getParent());
                    Files.copy(screenshot.toPath(), destination);
                    
                    System.out.println("Screenshot guardado en: " + destination);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}