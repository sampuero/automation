// src/main/java/com/portafolio/automation/pages/LoginPage.java
package com.portafolio.automation.pages;

import com.portafolio.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    
    @FindBy(css = "[data-test='username']")
    private WebElement usernameInput;
    
    @FindBy(css = "[data-test='password']")
    private WebElement passwordInput;
    
    @FindBy(css = "[data-test='login-button']")
    private WebElement loginButton;
    
    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }
    
    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    
    public void clickLogin() {
        loginButton.click();
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    
    public String getErrorMessage() {
        return errorMessage.getText();
    }
    
    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }
}