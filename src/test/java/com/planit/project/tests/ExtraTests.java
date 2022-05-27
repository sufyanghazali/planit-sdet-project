package com.planit.project.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.planit.project.model.pages.LoginPage;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class ExtraTests extends BaseTestSuite
{
    @Test
    public void verifyIncorrectPasswordError()
    {
        String errorMessage = new LoginPage(driver)
                .setUsername("standard_user")
                .setPassword("asdf")
                .clickLoginButton()
                .getErrorMessage();

        assertEquals("Epic sadface: Username and password do not match any user in this service", errorMessage);
    }

    @Test
    public void verifyAddProductToCart()
    {
        List<String> items = new LoginPage(driver)
                .loginAs("standard_user", "secret_sauce")
                .addFirstItemOnPageToCart()
                .clickShoppingCartLink()
                .getItemsInCart();

        assertEquals("[Sauce Labs Backpack]", items.toString());
    }

    @Test
    public void verifyAddProductsToCart()
    {
        List<String> items = new LoginPage(driver)
                .loginAs("standard_user", "secret_sauce")
                .addItemsOnPageToCart(3)
                .clickShoppingCartLink()
                .getItemsInCart();

        assertEquals("[Sauce Labs Backpack, Sauce Labs Bike Light, Sauce Labs Bolt T-Shirt]", items.toString());
    }

    @Test
    public void verifyRemoveItemFromCart()
    {
        List<String> items = new LoginPage(driver)
                .loginAs("standard_user", "secret_sauce")
                .addItemToCartByItemName("Sauce Labs Backpack")
                .addItemToCartByItemName("Sauce Labs Bike Light")
                .addItemToCartByItemName("Sauce Labs Fleece Jacket")
                .clickShoppingCartLink()
                .removeItemInCartByItemName("Sauce Labs Bike Light")
                .getItemsInCart();

        assertEquals("[Sauce Labs Backpack, Sauce Labs Fleece Jacket]", items.toString());
    }

    @Test
    public void verifySuccessPurchaseScenario()
    {
        WebElement completeHeader = new LoginPage(driver)
                .loginAs("standard_user", "secret_sauce")
                .addItemToCartByItemName("Sauce Labs Backpack")
                .addItemToCartByItemName("Sauce Labs Fleece Jacket")
                .clickShoppingCartLink()
                .clickCheckoutButton()
                .setFirstName("Sufyan")
                .setLastName("Ghazali")
                .setPostcode("3000")
                .clickContinueButton()
                .clickFinishButton()
                .getCompleteHeader();

        assertEquals("THANK YOU FOR YOUR ORDER", completeHeader.getText());
    }
}
