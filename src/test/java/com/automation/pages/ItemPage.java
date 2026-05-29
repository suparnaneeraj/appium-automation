package com.automation.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ItemPage extends BasePage {

    private final By addToCartButton = By.xpath("//*[@content-desc='Add To Cart button']");
    private final By itemPageTitle = By.xpath("//*[@content-desc='container header']/android.widget.TextView");

    private final By itemsCountInCart =By.xpath("//*[@content-desc='cart badge']//android.widget.TextView");
    private final By itemCounter = By.xpath("//*[@content-desc='counter amount']//android.widget.TextView");
    public ItemPage(AndroidDriver driver){
        super(driver);
    }
    public void clickAddToCart(){
        waitForVisibility(addToCartButton).click();
    }

    public String getItemPageTitle(){
        return waitForVisibility(itemPageTitle).getText();

    }

    public Integer getItemsCountInCard(){
        return Integer.parseInt(waitForVisibility(itemsCountInCart).getText());

    }

    public Integer getItemCounter(){
        return Integer.parseInt(driver.findElement(itemCounter).getText());
    }
    public void clickCart(){
        waitForVisibility(itemsCountInCart).click();
    }
}
