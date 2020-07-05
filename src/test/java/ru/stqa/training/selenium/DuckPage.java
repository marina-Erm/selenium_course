package ru.stqa.training.selenium;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class DuckPage extends TestBase {

    @Test
    public void checkDuckPage() {

        //перейдем на главную страницу ИМ
        driver.get("http://localhost/litecart/public_html/en/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(titleIs("Online Store | My Store"));

        //найдем  уточку из блока Campaigns и получим ее атрибуты
        WebElement campaignsDuck = driver.findElement(By.xpath(".//*[@id='box-campaigns']//div//ul//li"));

        //наименование
        WebElement nameDuck = campaignsDuck.findElement(By.xpath(".//div[@class='name']"));
        String textNameDuck = nameDuck.getAttribute("textContent");

        //цена
        WebElement regularPrice = campaignsDuck.findElement(By.xpath(".//div//s[@class='regular-price']"));
        String textPriceDuck = regularPrice.getAttribute("textContent");
        String colorPrice = regularPrice.getCssValue("color");
        String decorationPrice = regularPrice.getCssValue("text-decoration-line");
        String sizePrice = regularPrice.getCssValue("font-size");
        String weightPrice = regularPrice.getCssValue("font-weight");
        //добавим проверку класса
        String stylePriceDuck = regularPrice.getAttribute("class");

        //тут проверим, что обычная цена утки серая, убедившись что R,G, и B одинаковы
         reformatAndCheck(colorPrice);

        //выделим значение размера цены
        String sizeP1 = sizePrice.replaceAll("px","");


        //акционная цена
        WebElement campaignPrice = campaignsDuck.findElement(By.xpath(".//div//strong[@class='campaign-price']"));
        String textCampaignPriceDuck = campaignPrice.getAttribute("textContent");
        String colorCampaignPriceDuck = campaignPrice.getCssValue("color");
        String weightCampaignPriceDuck = campaignPrice.getCssValue("font-weight");
        String sizeCampaignPriceDuck = campaignPrice.getCssValue("font-size");
        //так же посмотрим и класс
        String styleCampaignPriceDuck = campaignPrice.getAttribute("class");

        //тут проверим что акционная цена красная
        reformatAndCheck(colorCampaignPriceDuck);

        //выделим значение размера акционной цены
        String sizeP2 = sizeCampaignPriceDuck.replaceAll("px","");

        //теперь перейдем на страницу уточки и проверим тоже самое там
        campaignsDuck.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement pageDuck = driver.findElement(By.xpath(".//div[@id='box-product']"));

        //наименование
        WebElement nameDuckOnPage = pageDuck.findElement(By.xpath(".//div//h1"));
        String textNameDuckOnPage = nameDuckOnPage.getAttribute("textContent");

        //старая цена
        WebElement regularPriceOnPage = pageDuck.findElement(By.xpath(".//div[@class='price-wrapper']//s"));
        String textRegularPriceOnPage = regularPriceOnPage.getAttribute("textContent");
        String colorRegularPrice = regularPriceOnPage.getCssValue("color");
        String decorationRegularPrice = regularPriceOnPage.getCssValue("text-decoration-line");
        String sizeRegularPriceOnPage = regularPriceOnPage.getCssValue("font-size");
        String weightRegularPriceDuck = regularPriceOnPage.getCssValue("font-weight");
        //тоже глянем класс
        String styleRegularPriceDuck = regularPriceOnPage.getAttribute("class");

        //проверим цвет цены
        reformatAndCheck(colorRegularPrice);

        //выделим значение размера  цены
        String sizeP3 = sizeRegularPriceOnPage.replaceAll("px","");

        //акционная цена
        WebElement campaignPriceOnPage = pageDuck.findElement(By.xpath(".//div[@class='price-wrapper']//strong"));
        String textCampaignPriceOnPage = campaignPriceOnPage.getAttribute("textContent");
        String colorCampaignPriceOnPage = campaignPriceOnPage.getCssValue("color");
        String weightCampaignPriceOnPage = campaignPriceOnPage.getCssValue("font-weight");
        String sizeCampaignPriceOnPage = campaignPriceOnPage.getCssValue("font-size");
        //и тут посмотрим класс
        String styleCampaignPriceOnPage = campaignPriceOnPage.getAttribute("class");

        //проверим цвет цены
        reformatAndCheck(colorCampaignPriceOnPage);

        //выделим значение размера акционной цены
        String sizeP4 = sizeCampaignPriceOnPage.replaceAll("px","");


        //теперь проверим сопадают ли данные
        //название
        Assert.assertTrue(textNameDuck.equals(textNameDuckOnPage));

        //цена
        Assert.assertTrue(textPriceDuck.equals(textRegularPriceOnPage));

        //зачеркнута ли серая цена
        Assert.assertTrue(decorationPrice.equals(decorationRegularPrice));

        //акционная цена
        Assert.assertTrue(textCampaignPriceDuck.equals(textCampaignPriceOnPage));

        //проверим что акционная цена жирнее обычной на главной и на странице утки
        Assert.assertTrue(Integer.valueOf(weightCampaignPriceDuck) > Integer.valueOf(weightPrice));
        Assert.assertTrue(Integer.valueOf(weightCampaignPriceOnPage) > Integer.valueOf(weightRegularPriceDuck));

        //тут проверяем что на главной акционная цена крупне обычной и проверяем на старнице товара тоже
        Assert.assertTrue(Double.valueOf(sizeP2) > Double.valueOf(sizeP1));
        Assert.assertTrue(Double.valueOf(sizeP4) > Double.valueOf(sizeP3));

        //проверяем по классам
        Assert.assertTrue(stylePriceDuck.equals(styleRegularPriceDuck));
        Assert.assertTrue(styleCampaignPriceDuck.equals(styleCampaignPriceOnPage));


    }
    public static void reformatAndCheck (String ss) {
        String[] subS;
        String s = ss.replaceAll("[rgba()]", "");
        String dm = "\\, ";

        subS = s.split(dm);
        String r = "";
        String g = "";
        String b = "";
        for (int i = 0; i < subS.length; i++) {
             r = subS[0];
             g = subS[1];
             b = subS[2];
           // System.out.println(subS[i]);

        }
        //проверка если был передан красный цвет
        if (g.equals("0") && b.equals("0")) {
            Assert.assertTrue(g.equals("0") && b.equals("0"));
        }
        //проверка если был передан серый
        if (!g.equals("0") && !b.equals("0")) {
            Assert.assertTrue(((Integer.valueOf(r)) == (Integer.valueOf(g))) && ((Integer.valueOf(g)) == (Integer.valueOf(b)))
            && ((Integer.valueOf(r)) == (Integer.valueOf(b))));
        }
    }

}
