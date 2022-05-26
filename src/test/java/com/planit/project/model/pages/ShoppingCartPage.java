package com.planit.project.model.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage
{
    public ShoppingCartPage(WebDriver driver)
    {
        super(driver);
    }

    public List<String> getItemsInCart()
    {
        return driver
                .findElements(By.className("inventory_item_name"))
                .stream()
                .map(i -> i.getText())
                .collect(Collectors.toList());
    }

    public ShoppingCartPage removeItemInCartByItemName(String itemName)
    {
        driver.findElements(By.className("cart_item")) // get list of products
                .stream()
                .filter(product -> product
                        .findElement(By.className("inventory_item_name")) // filter results by desired item name
                        .getText()
                        .equals(itemName))
                .collect(Collectors.toList())
                .get(0)
                .findElement(By.className("cart_button")) // get corresponding button for filtered result
                .click();

        return this;
    }

    public CheckoutInfoPage clickCheckoutButton()
    {
        driver.findElement(By.id("checkout")).click();

        return new CheckoutInfoPage(driver);
    }
}
