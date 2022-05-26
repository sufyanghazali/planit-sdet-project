package com.planit.project.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage
{
    public CheckoutOverviewPage(WebDriver driver)
    {
        super(driver);
    }

    public CheckoutCompletePage clickFinishButton()
    {
        driver.findElement(By.id("finish")).click();
        return new CheckoutCompletePage(driver);
    }
}
