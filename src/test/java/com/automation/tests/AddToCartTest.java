package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.ItemPage;
import com.automation.pages.MyCartPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class AddToCartTest extends BaseTest{

    @Test
    @Description("Verify user can add product to cart successfully")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldVerifyAddToCart (){
        String item =testDataReader.getData("item");
        HomePage homePage= new HomePage(driver);
        homePage.clickOptionByText(item);
        ItemPage itemPage = new ItemPage(driver);
        Assert.assertEquals(itemPage.getItemPageTitle(),item);
        Assert.assertEquals(itemPage.getItemCounter(),1);
        itemPage.clickAddToCart();
        Assert.assertEquals(itemPage.getItemsCountInCart(),1);

    }

    @Test
    @Description("Verify the items in the cart")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldVerifyItemsInCart(){
        String item =testDataReader.getData("item");
        HomePage homePage= new HomePage(driver);
        homePage.clickOptionByText(item);
        ItemPage itemPage = new ItemPage(driver);
        itemPage.clickAddToCart();
        itemPage.clickCart();
        MyCartPage myCartPage = new MyCartPage(driver);
        Assert.assertEquals(myCartPage.getPageTitle(),"My Carts");
        ArrayList<String> listOfItems=myCartPage.getItemsInCart();
        System.out.println(listOfItems);
        Assert.assertTrue(listOfItems.contains(item));
    }
}
