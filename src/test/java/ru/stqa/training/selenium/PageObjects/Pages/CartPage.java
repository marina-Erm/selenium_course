package ru.stqa.training.selenium.PageObjects.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
    }
    public void goToCart() {
        driver.findElement(By.xpath("//div[@id='cart']//a[@class='link']")).click();
        wait.until(titleIs("Checkout | My Store"));
    }

    public void removeAll() {
        List<WebElement> removeButtons = driver.findElements(By.xpath("//form[@name='cart_form']//button[@name='remove_cart_item']"));
        for (int k = 0; k < removeButtons.size(); k++) {
            List<WebElement> rowsCount = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr"));
            WebElement remove = driver.findElement(By.xpath("//form[@name='cart_form']//button[@name='remove_cart_item']"));
            wait.until(visibilityOf(remove)).click();
            if (k!=removeButtons.size()-1) {
             wait.until(numberOfElementsToBe((By.xpath("//table[@class='dataTable rounded-corners']//tr")), rowsCount.size() - 1));
            }
        }
    }

    public boolean cartIsEmpty() {
        return driver.findElement(By.tagName("p")).getAttribute("textContent").contains("There are no items in your cart.");

    }
}
