package com.planit.project.model.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        // put this wait here because it would be weird to put it in
        // getInventoryContainer()
        // was thinking of using a url but...
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(driver -> driver.findElement(By.id("inventory_container")));

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
