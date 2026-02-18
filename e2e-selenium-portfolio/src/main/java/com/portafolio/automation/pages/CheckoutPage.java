// src/main/java/com/portafolio/automation/pages/CheckoutPage.java
package com.portafolio.automation.pages;

import com.portafolio.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    
    @FindBy(id = "first-name")
    private WebElement firstNameInput;
    
    @FindBy(id = "last-name")
    private WebElement lastNameInput;
    
    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;
    
    @FindBy(id = "continue")
    private WebElement continueButton;
    
    @FindBy(id = "finish")
    private WebElement finishButton;
    
    @FindBy(className = "complete-header")
    private WebElement completeHeader;
    
    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;
    
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterPersonalInfo(String firstName, String lastName, String postalCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
    }
    
    public void clickContinue() {
        continueButton.click();
    }
    
    public void clickFinish() {
        finishButton.click();
    }
    
    public String getCompleteMessage() {
        return completeHeader.getText();
    }
    
    public String getTotalAmount() {
        return totalLabel.getText();
    }
}