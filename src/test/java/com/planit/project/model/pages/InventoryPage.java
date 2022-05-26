package com.planit.project.model.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage
{
    public InventoryPage(WebDriver driver)
    {
        super(driver);
    }

    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    public String getProductPrices()
    {
        List<WebElement> list = driver.findElements(By.className("inventory_item_price"));
        List<Double> prices = new ArrayList<>();

        for (WebElement price : list)
        {
            prices.add(Double.parseDouble(
                    price.getText().substring(1)));
        }

        return prices.toString();
    }

    public String getErrorMessage()
    {
        return driver.findElement(By.className("error-message-container")).getText();
    }

    public InventoryPage addFirstItemOnPageToCart()
    {
        driver.findElements(By.className("btn_inventory")).get(0).click();
        return this;
    }

    public InventoryPage addItemsOnPageToCart(int quantity)
    {
        List<WebElement> products = driver.findElements(By.className("btn_inventory"));

        for (int i = 0; i < quantity; i++)
        {
            products.get(i).click();
        }

        return this;
    }

    public InventoryPage addItemToCartByItemName(String itemName)
    {
        // readability probably suffering
        driver.findElements(By.className("inventory_item")) // get list of products
                .stream()
                .filter(product -> product
                        .findElement(By.className("inventory_item_name")) // filter results by desired item name
                        .getText()
                        .equals(itemName))
                .collect(Collectors.toList())
                .get(0)
                .findElement(By.className("btn_inventory")) // get corresponding button for filtered result
                .click();

        return this;
    }

    public ShoppingCartPage clickShoppingCartLink()
    {
        driver.findElement(By.className("shopping_cart_link")).click();
        return new ShoppingCartPage(driver);
    }

    public InventoryPage selectOption(String optionText)
    {
        new Select(driver.findElement(By.className("product_sort_container")))
                .selectByVisibleText(optionText);

        return this;
    }
}
