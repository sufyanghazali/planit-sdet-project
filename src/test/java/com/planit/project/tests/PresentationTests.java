package com.planit.project.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.planit.project.model.pages.InventoryPage;
import com.planit.project.model.pages.LoginPage;

import org.junit.jupiter.api.Test;

public class PresentationTests extends BaseTestSuite
{
    @Test
    public void verifySuccessfulLogin()
    {
        InventoryPage inventoryPage = new LoginPage(driver)
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLoginButton();

        assertEquals("https://www.saucedemo.com/inventory.html", inventoryPage.getCurrentUrl());
    }

    @Test
    public void verifyAddProductToCartByItemName()
    {
        List<String> items = new LoginPage(driver)
                .loginAs("standard_user", "secret_sauce")
                .addItemToCartByItemName("Sauce Labs Backpack")
                .addItemToCartByItemName("Sauce Labs Fleece Jacket")
                .addItemToCartByItemName("Sauce Labs Onesie")
                .clickShoppingCartLink()
                .getItemsInCart();

        assertEquals("[Sauce Labs Backpack, Sauce Labs Fleece Jacket, Sauce Labs Onesie]", items.toString());
    }

    @Test
    public void verifySortPriceLowToHigh()
    {
        InventoryPage products = new LoginPage(driver)
                .loginAs("standard_user", "secret_sauce")
                .selectOption("Price (low to high)");

        String expected = Arrays.toString(new Double[]
        { 7.99, 9.99, 15.99, 15.99, 29.99, 49.99 });

        assertEquals(expected, products.getProductPrices());
    }
}
