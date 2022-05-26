package com.planit.project.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage
{
    public CheckoutInfoPage(WebDriver driver)
    {
        super(driver);
    }

    public CheckoutInfoPage setFirstName(String firstName)
    {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        return this;
    }

    public CheckoutInfoPage setLastName(String lastName)
    {
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        return this;
    }

    public CheckoutInfoPage setPostcode(String postCode)
    {
        driver.findElement(By.id("postal-code")).sendKeys(postCode);
        return this;
    }

    public CheckoutOverviewPage clickContinueButton()
    {
        driver.findElement(By.id("continue")).click();
        return new CheckoutOverviewPage(driver);
    }
}
