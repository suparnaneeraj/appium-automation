package com.automation.tests;

import com.automation.utils.TestDataReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BaseTest {

    protected AndroidDriver driver;
    protected TestDataReader testDataReader = new TestDataReader();
    @BeforeClass
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
            options.setCapability("ignoreHiddenApiPolicyError", true);
            options.setCapability("disableWindowAnimation", true);
            driver = new AndroidDriver(URI.create(testDataReader.getData("serverUrl")).toURL(),options);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }

    }

    public void takeScreenshot() {
        try {
            File source = driver.getScreenshotAs(OutputType.FILE);

            File screenshotDir = new File("target/screenshots");
            screenshotDir.mkdirs();

            File destination = new File(
                    screenshotDir,
                    "failure-" + System.currentTimeMillis() + ".png");

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            Allure.addAttachment(
                    "Failure Screenshot",
                    new FileInputStream(destination));

            System.out.println("Screenshot saved: "
                    + destination.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Failed to take screenshot: "
                    + e.getMessage());
        }
    }

    @AfterMethod
    public void captureFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
