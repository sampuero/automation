// src/test/java/com/portafolio/automation/base/BaseTest.java
package com.portafolio.automation.base;

import com.portafolio.automation.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {	
	
    protected WebDriver driver;
    protected Properties config;
    
    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) throws IOException {
        // Cargar configuración
        config = new Properties();
        FileInputStream fis = new FileInputStream("resources/config.properties");
        config.load(fis);
        
        // Inicializar driver
        driver = WebDriverFactory.createDriver(browser);
        driver.get(config.getProperty("url"));
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    // IMPORTANTE: Getter para acceder al driver desde el listener
    public WebDriver getDriver() {
        return driver;
    }
}