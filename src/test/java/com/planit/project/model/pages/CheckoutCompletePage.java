package com.planit.project.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutCompletePage extends BasePage
{
    public CheckoutCompletePage(WebDriver driver)
    {
        super(driver);
    }

    public WebElement getCompleteHeader()
    {
        return driver.findElement(By.className("complete-header"));
    }
}
