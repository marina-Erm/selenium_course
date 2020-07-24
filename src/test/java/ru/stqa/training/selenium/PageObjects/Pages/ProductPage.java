package ru.stqa.training.selenium.PageObjects.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class ProductPage extends Page{
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addDuck() {
        Integer сountInCart = getCountProductInCart();
        WebElement duckAdd = driver.findElement(By.xpath("//div[@class='information']//button[@name='add_cart_product']"));
        duckAdd.click();
        wait.until(textToBePresentInElementLocated(By.xpath("//div[@id='cart']//span[@class='quantity']"), String.valueOf(сountInCart + 1)));

    }

    public Integer getCountProductInCart() {
        WebElement count = driver.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']"));
        return Integer.valueOf(count.getText());
    }
}
