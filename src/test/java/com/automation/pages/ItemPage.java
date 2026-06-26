package com.automation.pages;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ItemPage extends BasePage {

    private final By addToCartButton = By.xpath("//*[@content-desc='Add To Cart button']");
    private final By itemPageTitle = By.xpath("//*[@content-desc='container header']/android.widget.TextView");

    private final By itemsCountInCart =By.xpath("//*[@content-desc='cart badge']//android.widget.TextView");
    private final By itemCounter = By.xpath("//*[@content-desc='counter amount']//android.widget.TextView");
    public ItemPage(AndroidDriver driver){
        super(driver);
    }

    @Step("Click Add to cart button")
    public void clickAddToCart(){
        waitForClickable(addToCartButton).click();
    }

    @Step("Get the title of item details page")
    public String getItemPageTitle(){
        return waitForVisibility(itemPageTitle).getText();

    }

    @Step("Get the count of items in the cart")
    public Integer getItemsCountInCart(){
        return Integer.parseInt(waitForVisibility(itemsCountInCart).getText());

    }

    @Step("Get the number of items to be added")
    public Integer getItemCounter(){
        return Integer.parseInt(driver.findElement(itemCounter).getText());
    }

    @Step("Click on cart")
    public void clickCart(){
        waitForClickable(itemsCountInCart).click();
    }
}
