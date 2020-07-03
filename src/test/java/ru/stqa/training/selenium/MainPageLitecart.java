package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MainPageLitecart extends TestBase {

    @Test
    public void checkSticker() {
        driver.get("http://localhost/litecart/public_html/en/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //ищем всех уточек
        List<WebElement> ducks = driver.findElements(By.xpath("//ul[@class='listing-wrapper products']//li"));
        System.out.println("Total ducks on page = " + ducks.size());

        //тогда просто проверяем наличие класса sticker на каждой уточке
        for (WebElement stickerOnDuck : ducks) {
            List<WebElement> sticker = stickerOnDuck.findElements(By.xpath(".//div[contains(@class,'sticker')]"));

            //проверяем сколько стикеров на уточке
            if (!(sticker.size()==1)) {
                assert (sticker.size()!=1 );
                System.out.println("Duck have 0 or more 1 stickers");
                }
            System.out.println("Duck have sticker  - "+sticker.size());
        }
    }
}


