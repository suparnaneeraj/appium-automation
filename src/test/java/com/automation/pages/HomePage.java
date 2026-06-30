package com.automation.pages;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By hamburgerMenu = By.xpath("//*[@content-desc='open menu']");
    private final By productsHeader = By.xpath("//*[@content-desc='container header']/android.widget.TextView");

    public HomePage(AndroidDriver driver){
       super(driver);
    }

    @Step("Click product option: {option}")
    public void clickOptionByText(String option){
        By optionLocator = By.xpath("//*[@content-desc='store item']//*[@text='"+option+"' and @content-desc='store item text']");
        waitForVisibility(optionLocator).click();
    }

    @Step("Get the page title")
    public String getPageTitle(){
        return driver.findElement(productsHeader).getText();
    }

}
