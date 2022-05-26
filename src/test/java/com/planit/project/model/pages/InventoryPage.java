package com.planit.project.model.pages;

import java.util.ArrayList;
import java.util.List;

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

    public ShoppingCartPage clickShoppingCartLink()
    {
        driver.findElement(By.className("shopping_cart_link")).click();
        return new ShoppingCartPage(driver);
    }

    public InventoryPage selectLowToHigh()
    {
        new Select(driver.findElement(By.className("product_sort_container")))
                .selectByValue("lohi");

        return this;
    }
}
