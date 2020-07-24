package ru.stqa.training.selenium.PageObjects.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openMainPage() {
        driver.get("http://localhost/litecart/public_html/en/");
        wait.until(titleIs("Online Store | My Store"));
    }


    public void chooseAndAddProduct() {
        for (int i = 0; i < 3; i++) {
            driver.findElement(By.xpath("//div[@id='box-most-popular']//li//div[@class='sticker new']")).click();
            wait.until(titleContains("Rubber Ducks | My Store"));
            new ProductPage(driver).addDuck();
            openMainPage();
        }
    }
}
