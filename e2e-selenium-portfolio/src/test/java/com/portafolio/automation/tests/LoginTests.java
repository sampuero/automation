// src/test/java/com/portafolio/automation/tests/LoginTests.java
package com.portafolio.automation.tests;

import com.portafolio.automation.base.BaseTest;
import com.portafolio.automation.pages.InventoryPage;
import com.portafolio.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    
    @Test(description = "Verificar login exitoso con usuario estándar")
    public void testLoginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        
        loginPage.login("standard_user", "secret_sauce");
        
        Assert.assertTrue(inventoryPage.isInventoryDisplayed(), 
            "El inventario debería mostrarse después del login exitoso");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
    
    @Test(description = "Verificar mensaje de error con usuario bloqueado")
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.login("locked_out_user", "secret_sauce");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), 
            "Epic sadface: Sorry, this user has been locked out.");
    }
    
    @Test(description = "Verificar validación de campos vacíos")
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.clickLogin();
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), 
            "Epic sadface: Username is required");
    }
    
    @Test(description = "Verificar login con contraseña incorrecta")
    public void testInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.login("standard_user", "wrong_password");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), 
            "Epic sadface: Username and password do not match any user in this service");
    }
}