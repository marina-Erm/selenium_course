package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ProductCart extends TestBase {

    @Test
    public void addAndRemoveCart() throws InterruptedException {
        //перейдем на главную страницу ИМ
        driver.get("http://localhost/litecart/public_html/en/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(titleIs("Online Store | My Store"));

        //смотрим сколько продутов в корзине
        WebElement count = driver.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']"));
        Integer сountInCart = Integer.valueOf(count.getText());

        //заполним корзину тремя уточками
        for (int i = 0; i < 3; i++) {
            //возьмем первую уточку из блока Most Popular и перейдем на ее страницу
            driver.findElement(By.xpath("//div[@id='box-most-popular']//li[1]")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            assertTrue(isElementPresent(By.tagName("h1")));

            //добавим уточку в корзину дождемся изменения кол-ва товаров в корзине и вернемся на главную страницу
            WebElement sticker = driver.findElement(By.xpath("//div[@class='images-wrapper']//a//div[contains(@class,'sticker')]"));
            String stickerTest = sticker.getAttribute("textContent");

            //здесь проверим, если уточка со стикером Sale, то для нее надо выбрать желаемый размер перед добавлением в корзину
            if (stickerTest.equals("Sale")) {
                WebElement selectorSize = driver.findElement(By.xpath("//select[@name='options[Size]']"));
                WebElement selectSize = driver.findElement(By.xpath("//select[@name='options[Size]']//option[@value='Small']"));
                new Actions(driver).moveToElement(selectorSize)
                        .click(selectorSize)
                        .sendKeys(Keys.END)
                        .sendKeys(Keys.ENTER)
                        .perform();
            }
            //добавим уточку и увеличим счетчик корзины
            WebElement duckAdd = driver.findElement(By.xpath("//div[@class='information']//button[@name='add_cart_product']"));
            duckAdd.click();
            сountInCart++;
            String newCount = String.valueOf(сountInCart);

            //подождем пока текст на элементе изменится и вернемся на главную
            wait.until(textToBePresentInElementLocated(By.xpath("//div[@id='cart']//span[@class='quantity']"), newCount));
            driver.findElement(By.xpath("//div[@id='logotype-wrapper']")).click();
            wait.until(titleIs("Online Store | My Store"));
        }

        //перейдем в корзину
        driver.findElement(By.xpath("//div[@id='cart']//a[@class='link']")).click();
        wait.until(titleIs("Checkout | My Store"));

        //запомним сколько кнопок удалить на странице
        List<WebElement> removeButtons = driver.findElements(By.xpath("//form[@name='cart_form']//button[@name='remove_cart_item']"));

        //удалим уточек
        for (int k = 0; k < removeButtons.size(); k++) {
            //пересчитаем кол-во кнопок
            List<WebElement> removeButtons2 = driver.findElements(By.xpath("//form[@name='cart_form']//button[@name='remove_cart_item']"));
            Integer sizeRemBut = removeButtons2.size();

            //посчитаем кол-во строк в таблице внизу страницы
            List<WebElement> rowsCount = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr"));

            //удалим уточку и уменьшим кол-во кнопок remove на одну
            WebElement remove = driver.findElement(By.xpath("//form[@name='cart_form']//button[@name='remove_cart_item']"));
            wait.until(visibilityOf(remove)).click();
            sizeRemBut = sizeRemBut - 1;

            //если у нас кол-во кнопок не ноль, то ждем пока кол-во строк в таблице обновится,
            // иначе ждем сообщения, что корзина пуста
            if (sizeRemBut != 0) {
                wait.until(numberOfElementsToBe((By.xpath("//table[@class='dataTable rounded-corners']//tr")), rowsCount.size() - 1));
            } else {
                wait.until(textToBePresentInElementLocated(By.xpath("//td[@class='content']//p//em"), "There are no items in your cart."));
            }
        }
    }
}