package ru.stqa.training.selenium;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


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

        //добавим проверку класса
        String stylePriceDuck = regularPrice.getAttribute("class");


        //тут проверим, что обычная цена утки серая, убедившись что R,G, и B одинаковы (для Firefox)
  /*         char[] firstFF = new char[7 - 4];
        char[] secondFF = new char[12 - 9];
        char[] thirdFF = new char[17 - 14];
        colorPrice.getChars(4, 7, firstFF, 0);
        colorPrice.getChars(9, 12, secondFF, 0);
        colorPrice.getChars(14, 17, thirdFF, 0);

        Assert.assertTrue(String.valueOf(firstFF).equals(String.valueOf(secondFF)));
        Assert.assertTrue(String.valueOf(secondFF).equals(String.valueOf(thirdFF)));
        Assert.assertTrue(String.valueOf(thirdFF).equals(String.valueOf(firstFF)));
    */
//так выглядит проверка для Хрома, т.к. там немного по другому приходит значение
         char[] firstCh = new char[8 - 5];
            char[] secondCh = new char[13 - 10];
            char[] thirdCh = new char[18 - 15];
            colorPrice.getChars(5, 8, firstCh, 0);
            colorPrice.getChars(10, 13, secondCh, 0);
            colorPrice.getChars(15, 18, thirdCh, 0);

            Assert.assertTrue(String.valueOf(firstCh).equals(String.valueOf(secondCh)));
            Assert.assertTrue(String.valueOf(secondCh).equals(String.valueOf(thirdCh)));
            Assert.assertTrue(String.valueOf(thirdCh).equals(String.valueOf(firstCh)));


        //выделим значение размера цены
        char[] size1 = new char[2 - 0];
        sizePrice.getChars(0, 2, size1, 0);

        //акционная цена
        WebElement campaignPrice = campaignsDuck.findElement(By.xpath(".//div//strong[@class='campaign-price']"));
        String textCampaignPriceDuck = campaignPrice.getAttribute("textContent");
        String colorCampaignPriceDuck = campaignPrice.getCssValue("color");
        String weightCampaignPriceDuck = campaignPrice.getCssValue("font-weight");
        String sizeCampaignPriceDuck = campaignPrice.getCssValue("font-size");
        //так же посмотрим и класс
        String styleCampaignPriceDuck = campaignPrice.getAttribute("class");

        //выделим значение размера акционной цены
        char[] size2 = new char[2 - 0];
        sizeCampaignPriceDuck.getChars(0, 2, size2, 0);

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
        //тоже глянем класс
        String styleRegularPriceDuck = regularPriceOnPage.getAttribute("class");

        //тут тоже проверим, что цена серая, убедившись что R,G, и B одинаковы (для Firefox)
 /*        char[] first1 = new char[7 - 4];
        char[] second1 = new char[12 - 9];
        char[] third1 = new char[17 - 14];
        colorRegularPrice.getChars(4, 7, first1, 0);
        colorRegularPrice.getChars(9, 12, second1, 0);
        colorRegularPrice.getChars(14, 17, third1, 0);
        Assert.assertTrue(String.valueOf(first1).equals(String.valueOf(second1)));
        Assert.assertTrue(String.valueOf(second1).equals(String.valueOf(third1)));
        Assert.assertTrue(String.valueOf(third1).equals(String.valueOf(first1)));
*/
        //так будет выглядеть проверка для Хрома
     char[] first1Ch = new char[8 - 5];
        char[] second1Ch = new char[13 - 10];
        char[] third1Ch = new char[18 - 15];
        colorRegularPrice.getChars(5, 8, first1Ch, 0);
        colorRegularPrice.getChars(10, 13, second1Ch, 0);
        colorRegularPrice.getChars(15, 18, third1Ch, 0);

        Assert.assertTrue(String.valueOf(first1Ch).equals(String.valueOf(second1Ch)));
        Assert.assertTrue(String.valueOf(second1Ch).equals(String.valueOf(third1Ch)));
        Assert.assertTrue(String.valueOf(third1Ch).equals(String.valueOf(first1Ch)));


        //выделим значение размера  цены
        char[] size3 = new char[2 - 0];
        sizeRegularPriceOnPage.getChars(0, 2, size3, 0);

        //акционная цена
        WebElement campaignPriceOnPage = pageDuck.findElement(By.xpath(".//div[@class='price-wrapper']//strong"));
        String textCampaignPriceOnPage = campaignPriceOnPage.getAttribute("textContent");
        String colorCampaignPriceOnPage = campaignPriceOnPage.getCssValue("color");
        String weightCampaignPriceOnPage = campaignPriceOnPage.getCssValue("font-weight");
        String sizeCampaignPriceOnPage = campaignPriceOnPage.getCssValue("font-size");
        //и тут посмотрим класс
        String styleCampaignPriceOnPage = campaignPriceOnPage.getAttribute("class");

        //выделим значение размера акционной цены
        char[] size4 = new char[2 - 0];
        sizeCampaignPriceOnPage.getChars(0, 2, size4, 0);


        //теперь проверим сопадают ли данные
        Assert.assertTrue(textNameDuck.equals(textNameDuckOnPage));
        Assert.assertTrue(textPriceDuck.equals(textRegularPriceOnPage));

        Assert.assertTrue(decorationPrice.equals(decorationRegularPrice));

        Assert.assertTrue(textCampaignPriceDuck.equals(textCampaignPriceOnPage));
        Assert.assertTrue(colorCampaignPriceDuck.equals(colorCampaignPriceOnPage));
        //В Хроме жирность одинакова
        Assert.assertTrue(weightCampaignPriceDuck.equals(weightCampaignPriceOnPage));
        // для Firefox жирность на главной и на странице утки не совпадают,поэтому проверяем, что жирность есть, т.к. не жирный шрифт 400
        //Assert.assertTrue((Integer.valueOf(weightCampaignPriceDuck) > 400) && (Integer.valueOf(weightCampaignPriceOnPage) > 400));

        Assert.assertTrue(stylePriceDuck.equals(styleRegularPriceDuck));
        Assert.assertTrue(styleCampaignPriceDuck.equals(styleCampaignPriceOnPage));

        //тут проверяем что на главной акционная цена крупне обычной и на старнице товара так же
        Assert.assertTrue(Integer.valueOf(String.valueOf(size2)) > Integer.valueOf(String.valueOf(size1)));
        Assert.assertTrue(Integer.valueOf(String.valueOf(size4)) > Integer.valueOf(String.valueOf(size3)));
    }
}
