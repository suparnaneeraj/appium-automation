package com.automation.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ItemPage extends BasePage {

    private final By addToCartButton = By.xpath("//*[@content-desc='Add To Cart button']");
    private final By itemPageTitle = By.xpath("//*[@content-desc='container header']/android.widget.TextView");
    public ItemPage(AndroidDriver driver){
        super(driver);
    }
    public void clickAddToCart(){
        driver.findElement(addToCartButton).click();
    }

    public String getItemPageTitle(){
        return waitForVisibility(itemPageTitle).getText();

    }

}
