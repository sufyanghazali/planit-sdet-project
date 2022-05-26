package com.planit.project.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage
{
    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    public LoginPage setUsername(String username)
    {
        driver.findElement(By.id("user-name")).sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password)
    {
        driver.findElement(By.id("password")).sendKeys(password);
        return this;
    }

    public InventoryPage clickLoginButton()
    {
        driver.findElement(By.id("login-button")).click();

        return new InventoryPage(driver);
    }

    // business logic
    public InventoryPage loginAs(String username, String password)
    {
        return this.setUsername(username)
                .setPassword(password)
                .clickLoginButton();
    }
}
