package ru.stqa.training.selenium;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class AddNewDuck extends TestBase {

    @Test
    public void addNewDuck() throws IOException, InterruptedException {
        //зайдем в админку
        loginInAdmin();
        Thread.sleep(1000);
        //перейдем на страницу добавления товара
        driver.findElement(By.xpath("//*[@id='box-apps-menu']/li/a[contains(@href,'app=catalog')]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //соберем кол-во товаров
        WebElement count = driver.findElement(By.xpath("//table[@class='dataTable']//tr[@class='footer']/td"));
        String totalCount = count.getAttribute("textContent");

        //перейдем к добавлению товара
        driver.findElement(By.xpath("//*[@id='content']/div/a[contains(@href,'edit_product')]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //заполним вкладку General
        driver.findElement(By.xpath("//div[@id='tab-general']//input[@type='radio'][@value='1']")).click();
        driver.findElement(By.xpath("//span[@class='input-wrapper']//input[@name='name[en]']"))
                .sendKeys("Black Duck");
        driver.findElement(By.xpath("//div[@id='tab-general']//input[@name='code']")).sendKeys("RD999");
        driver.findElement(By.xpath("//div[@id='tab-general']//input[@data-name='Rubber Ducks']")).click();

        WebElement defaultCat = driver.findElement(By.xpath("//select[@name='default_category_id']"));
        WebElement rubDuck = defaultCat.findElement(By.xpath("//select[@name='default_category_id']/option[@value='1']"));
        new Actions(driver).moveToElement(defaultCat)
                .click()
                .moveToElement(rubDuck)
                .click(rubDuck)
                .perform();

        driver.findElement(By.xpath("//div[@class='input-wrapper']//input[@name='product_groups[]'][@value='1-3']")).click();

        WebElement quantity = driver.findElement(By.xpath("//div[@id='tab-general']//input[@name='quantity']"));
        quantity.clear();
        quantity.sendKeys("30");

        WebElement quantityUnit = driver.findElement(By.xpath("//div[@id='tab-general']//select[@name='quantity_unit_id']"));
        WebElement selQuantUn = quantityUnit.findElement(By.xpath(".//option[@value='1']"));
        new Actions(driver).moveToElement(quantity)
                .click()
                .moveToElement(selQuantUn)
                .click(selQuantUn)
                .perform();
        quantity.click();

        WebElement deliveryState = driver.findElement(By.xpath("//div[@id='tab-general']//select[@name='delivery_status_id']"));
        WebElement selDelState = quantityUnit.findElement(By.xpath(".//option[@value='1']"));
        new Actions(driver).moveToElement(deliveryState)
                .click()
                .moveToElement(selDelState)
                .click(selDelState)
                .perform();
        quantity.click();

        WebElement solOutStat = driver.findElement(By.xpath("//div[@id='tab-general']//select[@name='sold_out_status_id']"));
        WebElement selSolOutState = quantityUnit.findElement(By.xpath(".//option[@value='1']"));
        new Actions(driver).moveToElement(solOutStat)
                .click()
                .moveToElement(selSolOutState)
                .click(selSolOutState)
                .perform();
        quantity.click();

        File file = new File("src/test/resources/Black_duck.jpg");
        String absolutePath = file.getAbsolutePath();
        WebElement image = driver.findElement(By.xpath("//div[@id='tab-general']//input[@type='file']"));
        image.sendKeys(absolutePath);

        WebElement validFrom = driver.findElement(By.xpath("//div[@id='tab-general']//input[@name='date_valid_from']"));
       //для Chrome
        try {
            new Actions(driver).moveToElement(validFrom, 0, 0).click(validFrom).sendKeys("07-07-2020").sendKeys(Keys.ENTER).perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //для firefox
        if (validFrom.getCssValue("value")!="2020-07-07") validFrom.sendKeys("2020-07-07");


        WebElement validTo = driver.findElement(By.xpath("//div[@id='tab-general']//input[@name='date_valid_to']"));
        //для Chrome
        try {
            new Actions(driver).moveToElement(validTo,0,0).click(validTo).sendKeys("07-07-2021").sendKeys(Keys.ENTER).perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //для firefox
        if (validTo.getCssValue("value")!="2021-07-07") validTo.sendKeys("2021-07-07");


        //уходим на вкладку Information
        driver.findElement(By.xpath("//ul[@class='index']//a[@href='#tab-information']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement manufacturer = driver.findElement(By.xpath("//div[@id='tab-information']//select[@name='manufacturer_id']"));
        WebElement acme = manufacturer.findElement(By.xpath(".//option[@value='1']"));
        new Actions(driver).moveToElement(manufacturer)
                .click(manufacturer)
                .moveToElement(acme)
                .click(acme)
                .perform();

        driver.findElement(By.xpath("//div[@id='tab-information']//input[@name='keywords']")).sendKeys("Black Duck");
        driver.findElement(By.xpath("//div[@id='tab-information']//input[@name='short_description[en]']"))
                .sendKeys("This is new model of duck");

        //заполняем описание Description из файла
        File fileDescr = new File("src/test/resources/Description_duck.txt");
        FileReader read = new FileReader(fileDescr);
        char[] s = new char[250];
        read.read(s);
        WebElement descr = driver.findElement(By.xpath("//span[@class='input-wrapper']//div[@class='trumbowyg-editor']"));
        descr.sendKeys(String.valueOf(s));

        driver.findElement(By.xpath("//div[@id='tab-information']//input[@name='head_title[en]']")).sendKeys("Black Duck");
        driver.findElement(By.xpath("//div[@id='tab-information']//input[@name='meta_description[en]']")).sendKeys("Black Duck");


        //уходим на вкладку Prices
        driver.findElement(By.xpath("//ul[@class='index']//a[@href='#tab-prices']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//div[@id='tab-prices']//input[@name='purchase_price']")).sendKeys("50");

        WebElement priceCode = driver.findElement(By.xpath("//div[@id='tab-prices']//select[@name='purchase_price_currency_code']"));
        WebElement usdSelect = priceCode.findElement(By.xpath(".//option[@value='USD']"));
        new Actions(driver).moveToElement(priceCode)
                .click(priceCode)
                .moveToElement(usdSelect)
                .click(usdSelect)
                .perform();

        WebElement taxUSD = driver.findElement(By.xpath("//div[@id='tab-prices']//input[@name='gross_prices[USD]']"));
        taxUSD.clear();
        taxUSD.sendKeys("5");

        //сохраним все изменения
        driver.findElement(By.xpath("//span[@class='button-set']//button[@name='save']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(200);
        assertTrue(isElementPresent(By.tagName("h1")));

        //првоерим, что уточка добавилась
        //соберем обновленное кол-во товаров
        WebElement countNew = driver.findElement(By.xpath("//table[@class='dataTable']//tr[@class='footer']/td"));
        String totalCountNew = countNew.getAttribute("textContent");

        Assert.assertFalse(totalCount.equals(totalCountNew));
    }
}
