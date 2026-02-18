package com.portafolio.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExtentReportListener implements IReporter {
    
    private ExtentReports extent;
    private ExtentSparkReporter sparkReporter;
    
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        // Inicializar el reporter
        sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // Configuración del reporte
        sparkReporter.config().setDocumentTitle("Reporte de Automatización E2E");
        sparkReporter.config().setReportName("SauceDemo - Pruebas Automatizadas");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
        sparkReporter.config().setEncoding("UTF-8");
        
        // Información del sistema
        extent.setSystemInfo("Sistema Operativo", System.getProperty("os.name"));
        extent.setSystemInfo("Java Versión", System.getProperty("java.version"));
        extent.setSystemInfo("Usuario", System.getProperty("user.name"));
        extent.setSystemInfo("Navegador", "Chrome / Firefox");
        extent.setSystemInfo("Ambiente", "QA");
        extent.setSystemInfo("URL", "https://www.saucedemo.com");
        
        // Recorrer todas las suites y tests para agregarlos al reporte
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> results = suite.getResults();
            
            for (ISuiteResult result : results.values()) {
                ITestContext context = result.getTestContext();
                
                // Crear test en el reporte
                ExtentTest extentTest = extent.createTest(
                    context.getName(),
                    context.getCurrentXmlTest().getAllParameters().toString()
                );
                
                // Agregar resultados de los tests pasados
                for (ITestResult testResult : context.getPassedTests().getAllResults()) {
                    extentTest.log(Status.PASS, getTestName(testResult));
                }
                
                // Agregar resultados de los tests fallidos
                for (ITestResult testResult : context.getFailedTests().getAllResults()) {
                    extentTest.log(Status.FAIL, getTestName(testResult) + " - Falló");
                }
                
                // Agregar resultados de los tests omitidos
                for (ITestResult testResult : context.getSkippedTests().getAllResults()) {
                    extentTest.log(Status.SKIP, getTestName(testResult) + " - Omitido");
                }
            }
        }
        
        // Generar el reporte final
        extent.flush();
    }
    
    private String getTestName(ITestResult result) {
        return result.getMethod().getMethodName() + " (" + result.getParameters() + ")";
    }
}