package com.automation.tests;

import com.automation.utils.TestDataReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
            options.setApp(testDataReader.getData("app"));
            driver = new AndroidDriver(URI.create(testDataReader.getData("serverUrl")).toURL(),options);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }

    }

    @AfterMethod
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

}
