package com.automation.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URI;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp (){
        try{
            UiAutomator2Options options= new UiAutomator2Options();
            options.setAutomationName("UiAutomator2");
            options.setDeviceName("emulator-5554");
            options.setPlatformName("Android");
            options.setApp("/Users/suparnaneeraj/Desktop/Suparna/Trainings/Appium/Android-MyDemoAppRN.1.3.0.build-244.apk");
            driver = new AndroidDriver(URI.create("http://127.0.0.1:4723/").toURL(),options);
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
