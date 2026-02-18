// src/test/java/com/portafolio/automation/tests/CheckoutTests.java
package com.portafolio.automation.tests;

import com.portafolio.automation.base.BaseTest;
import com.portafolio.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {
    
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    
    @BeforeMethod
    public void setupTest() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        
        // Login y agregar producto antes de cada prueba de checkout
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.goToCart();
    }
    
    @Test(description = "Flujo completo de compra")
    public void testCompleteCheckout() {
        cartPage.proceedToCheckout();
        
        // Información de envío
        checkoutPage.enterPersonalInfo("Juan", "Perez", "12345");
        checkoutPage.clickContinue();
        
        // Verificar resumen
        Assert.assertTrue(checkoutPage.getTotalAmount().contains("Total"));
        
        // Finalizar compra
        checkoutPage.clickFinish();
        
        // Verificar confirmación
        Assert.assertEquals(checkoutPage.getCompleteMessage(), 
            "Thank you for your order!");
    }
    
    @Test(description = "Verificar checkout sin información personal")
    public void testCheckoutWithoutInfo() {
        cartPage.proceedToCheckout();
        checkoutPage.clickContinue();
        
        // Debería mostrar error o no avanzar
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"));
    }
}