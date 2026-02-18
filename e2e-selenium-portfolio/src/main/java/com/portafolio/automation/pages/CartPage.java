// src/main/java/com/portafolio/automation/pages/CartPage.java
package com.portafolio.automation.pages;

import com.portafolio.automation.base.BasePage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    
    @FindBy(id = "checkout")
    private WebElement checkoutButton;
    
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;
    
    @FindBy(className = "continue_shopping")
    private WebElement continueShoppingButton;
    
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    public void proceedToCheckout() {
        checkoutButton.click();
    }
    
    public int getCartItemCount() {
        return cartItems.size();
    }
    
    public boolean isItemInCart(String itemName) {
        return cartItems.stream()
            .anyMatch(item -> item.getText().contains(itemName));
    }
}