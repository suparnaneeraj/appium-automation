package com.automation.pages;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By hamburgerMenu = By.xpath("//*[@content-desc='open menu']");

    public HomePage(AndroidDriver driver){
       super(driver);
    }

    @Step("Click product option: {option}")
    public void clickOptionByText(String option){
        By optionLocator = By.xpath("//*[@content-desc='store item text' and @text='"+option+"']");
        waitForVisibility(optionLocator).click();
    }

}
