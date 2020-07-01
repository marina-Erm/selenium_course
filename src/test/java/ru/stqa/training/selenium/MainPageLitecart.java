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

        //ищем сколько стикеров на каждой уточке
        Integer stickerCount = 0;
        for (WebElement stickerOnDuck : ducks) {
            List<WebElement> stickers = stickerOnDuck.findElements(By.xpath(".//div[contains(@class,'sticker')]"));
            stickerCount = stickerCount + stickers.size();
            System.out.println("Duck have stickers = " + stickerCount);
        }

        System.out.println("Total stickers = " + stickerCount);
        Assert.assertTrue(ducks.size() == stickerCount);
    }
}


