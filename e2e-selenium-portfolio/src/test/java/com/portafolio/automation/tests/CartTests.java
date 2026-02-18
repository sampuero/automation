// src/test/java/com/portafolio/automation/tests/CartTests.java
package com.portafolio.automation.tests;

import com.portafolio.automation.base.BaseTest;
import com.portafolio.automation.pages.CartPage;
import com.portafolio.automation.pages.InventoryPage;
import com.portafolio.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {
    
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    
    @BeforeMethod
    public void setupTest() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        
        // Login antes de cada prueba
        loginPage.login("standard_user", "secret_sauce");
    }
    
    @Test(description = "Agregar un producto al carrito")
    public void testAddSingleItemToCart() {
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.goToCart();
        
        Assert.assertEquals(cartPage.getCartItemCount(), 1);
        Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"));
    }
    
    @Test(description = "Agregar múltiples productos al carrito")
    public void testAddMultipleItemsToCart() {
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.addItemToCart("Sauce Labs Bike Light");
        inventoryPage.goToCart();
        
        Assert.assertEquals(cartPage.getCartItemCount(), 2);
    }
    
    @Test(description = "Verificar carrito vacío inicialmente")
    public void testEmptyCart() {
        inventoryPage.goToCart();
        Assert.assertEquals(cartPage.getCartItemCount(), 0);
    }
}