// src/main/java/com/portafolio/automation/pages/InventoryPage.java
package com.portafolio.automation.pages;

import com.portafolio.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InventoryPage extends BasePage {
    
    @FindBy(className = "inventory_list")
    private WebElement inventoryContainer;
    
    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;
    
    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;
    
    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;
    
    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    
    public boolean isInventoryDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(inventoryContainer)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void addItemToCart(String itemName) {
        // Encontrar el item por nombre y hacer clic en "Add to cart"
        String dynamicXpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", itemName);
        WebElement addButton = driver.findElement(org.openqa.selenium.By.xpath(dynamicXpath));
        addButton.click();
    }
    
    public void goToCart() {
        cartLink.click();
    }
    
    public int getItemCount() {
        return inventoryItems.size();
    }
}