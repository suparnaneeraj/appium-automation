package com.automation.tests;

import com.automation.utils.TestDataReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.net.URI;

public class BaseTest {

    protected AndroidDriver driver;
    protected TestDataReader testDataReader = new TestDataReader();
    @BeforeMethod
    public void setUp (){
        try{
            UiAutomator2Options options= new UiAutomator2Options();
            options.setAutomationName(testDataReader.getData("automationName"));
            options.setDeviceName(testDataReader.getData("deviceName"));
            options.setPlatformName(testDataReader.getData("platformName"));
            String appPath = System.getProperty("user.dir") + "/" + testDataReader.getData("app");
            options.setApp(appPath);
            options.setCapability("adbExecTimeout", Long.parseLong(testDataReader.getData("adbExecTimeout")));
            options.setCapability("uiautomator2ServerInstallTimeout",Long.parseLong(testDataReader.getData("uiautomator2ServerInstallTimeout")));
            options.setCapability("uiautomator2ServerLaunchTimeout", Long.parseLong(testDataReader.getData("uiautomator2ServerLaunchTimeout")));
            options.setCapability("androidInstallTimeout", Long.parseLong(testDataReader.getData("androidInstallTimeout")));
            options.setCapability("noReset", false);
            options.setCapability("autoGrantPermissions", true);
            driver = new AndroidDriver(URI.create(testDataReader.getData("serverUrl")).toURL(),options);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }

    }

    public void takeScreenshot() {
        byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
        System.out.println("Screenshot size: " + screenshot.length);
        Allure.addAttachment(
                "Failure Screenshot",
                new ByteArrayInputStream(screenshot)
        );

    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot();
        }
        if(driver!=null){
            driver.quit();
        }
    }

}
