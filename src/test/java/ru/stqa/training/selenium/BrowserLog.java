package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class BrowserLog extends TestBase {

    @Test
    public void browserLog() {

        //авторизуемся под админкой и перейдем на страницу с каталогом
        loginInAdmin();
        driver.get("http://localhost/litecart/public_html/admin/?app=catalog&doc=catalog&category_id=2");
        wait.until(titleIs("Catalog | My Store"));

        //соберем табличку со строками
       List<WebElement> tableDucks = driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']"));

       //пройдем циклом и прокликаем каждую уточку
       for (int i=3; i<tableDucks.size(); i++){
           List<WebElement> tableDucksNew = driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']"));
           WebElement selectDuck = tableDucksNew.get(i).findElements(By.tagName("td"))
                   .get(2).findElement(By.tagName("a"));
           selectDuck.click();
           wait.until(titleContains("Edit Product"));

           //проверим, что в логах браузера нет сообщений
            driver.manage().logs().get("browser").getAll().forEach(l -> {
               System.out.println(l);
               Assert.assertTrue(l.equals(""));
            });

            //вернемся назад на страницу с табличкой
           driver.navigate().back();
           wait.until(titleIs("Catalog | My Store"));
       }
    }
}
