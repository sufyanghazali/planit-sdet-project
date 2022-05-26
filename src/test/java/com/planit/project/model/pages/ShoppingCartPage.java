package com.planit.project.model.pages;

import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage
{
    public ShoppingCartPage(WebDriver driver)
    {
        super(driver);
    }

    public String getItemsInCart()
    {
        return driver
                .findElements(By.className("inventory_item_name"))
                .stream()
                .map(i -> i.getText())
                .collect(Collectors.toList())
                .toString();
    }

}
