package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AdminPanel extends TestBase {

    @Test
    public void adminPanelTest() throws InterruptedException {
        //авторизация под админкой
        driver.get("http://localhost/litecart/public_html/admin/login.php");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.tagName("button")).click();
        wait.until(titleIs("My Store"));

        //Узнаем кол-во пунктов в меню
        List<WebElement> listMenu = driver.findElements(By.xpath("//*[@id='box-apps-menu']/li"));

        //проходим циклом по меню, каждый раз обновляем список и кликаем по пункту
        for (int i = 0; i < listMenu.size(); i++) {
            List<WebElement> menu = driver.findElements(By.xpath("//*[@id='box-apps-menu']/li"));
            menu.get(i).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //проверяем наличие тега
            assertTrue(isElementPresent(By.tagName("h1")));
          // wait.until(presenceOfElementLocated(By.tagName("h1")));

            //проверяем есть ли подпункты в пункте меню и если да, то поступаем с ними аналогично
            if (driver.findElements(By.xpath("//ul[@class='docs']//li")).size() > 0) {
                List<WebElement> listSubparagraph = driver.findElements(By.xpath("//ul[@class='docs']//li"));
                for (int j = 0; j < listSubparagraph.size(); j++) {
                    List<WebElement> listSubparagraphMenu = driver.findElements(By.xpath("//ul[@class='docs']//li"));
                    listSubparagraphMenu.get(j).click();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    assertTrue(isElementPresent(By.tagName("h1")));
                    //  wait.until(presenceOfElementLocated(By.tagName("h1")));
                }
            }
        }
    }
}
