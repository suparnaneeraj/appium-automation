package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.ItemPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest{
    @Test
    public void shouldVerifyAddToCart (){
        String item = "Sauce Labs Bolt T-Shirt";
        HomePage homePage= new HomePage(driver);
        homePage.clickOptionByText(item);
        ItemPage itemPage = new ItemPage(driver);
        Assert.assertEquals(itemPage.getItemPageTitle(),item);
        itemPage.clickAddToCart();

    }
}
