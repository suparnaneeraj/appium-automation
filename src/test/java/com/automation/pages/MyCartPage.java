package com.automation.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MyCartPage extends BasePage {
    private final By pageTitle = By.xpath("//*[@content-desc='container header']/android.widget.TextView");
    private final By itemsInCart = By.xpath("//*[@content-desc='product label']");
    public MyCartPage(AndroidDriver driver){
        super(driver);
    }

    public String getPageTitle(){
        return waitForVisibility(pageTitle).getText();
    }

    public ArrayList<String> getItemsInCart(){
        ArrayList<String> listOfItemsInCart = new ArrayList<>();
        List<WebElement> items= driver.findElements(itemsInCart);
        for(WebElement element: items){
            listOfItemsInCart.add(element.getText().trim());
        }
        return listOfItemsInCart;
    }
}
