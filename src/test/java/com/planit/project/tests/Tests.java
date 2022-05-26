package com.planit.project.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import com.planit.project.model.pages.InventoryPage;
import com.planit.project.model.pages.LoginPage;

import org.junit.jupiter.api.Test;

public class Tests extends BaseTestSuite
{
    @Test
    // Justification: can only access https://www.saucedemo.com/inventory.html if
    // user is authenticated. redirected to login if try to access without login
    // need to account for response time
    public void verifySuccessfulLogin()
    {
        var inventoryPage = new LoginPage(driver)
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLoginButton();

        assertEquals("https://www.saucedemo.com/inventory.html", inventoryPage.getCurrentUrl());
    }

    @Test
    public void verifyAddProductToCart()
    {
        String items = new LoginPage(driver)
                .loginAs("standard_user", "secret_sauce")
                .addFirstItemOnPageToCart()
                .clickShoppingCartLink()
                .getItemsInCart();

        assertEquals("[Sauce Labs Backpack]", items);
    }

    @Test
    public void verifyAddProductsToCart()
    {
        String items = new LoginPage(driver)
                .loginAs("standard_user", "secret_sauce")
                .addItemsOnPageToCart(3)
                .clickShoppingCartLink()
                .getItemsInCart();

        assertEquals("[Sauce Labs Backpack, Sauce Labs Bike Light, Sauce Labs Bolt T-Shirt]", items);
    }

    @Test
    public void verifySortPriceLowToHigh()
    {
        InventoryPage products = new LoginPage(driver)
                .loginAs("standard_user", "secret_sauce")
                .selectLowToHigh();

        String expected = Arrays.toString(new Double[]
        { 7.99, 9.99, 15.99, 15.99, 29.99, 49.99 });

        assertEquals(
                expected,
                products.getProductPrices());
    }
}
