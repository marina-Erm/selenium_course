package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class OpenWindows extends TestBase {

    @Test
    public void openNewWindows() {

        //зайдем в админку и перейдем на вкладку со странами
        loginInAdmin();
        driver.findElement(By.xpath("//*[@id='box-apps-menu']/li/a[contains(@href,'doc=countries')]")).click();
        wait.until(titleIs("Countries | My Store"));


        //начнем создание новой страны
        driver.findElement(By.xpath("//a[@class='button']")).click();
        wait.until(titleIs("Add New Country | My Store"));

        //найдем все ссылки ведущие в новые окна
        List<WebElement> links = driver.findElements(By.xpath("//i[@class='fa fa-external-link']"));

        //запомним идентификатор текущего окна
        String originalWindow = driver.getWindowHandle();

        //прокликаем ссылки
        for (WebElement clickLink : links) {
            clickLink.click();

            //тут подождем пока число окон увеличится, после клика по ссылке
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            //соберем список открытых окон, удалим из него исходное окно и перейдем к оставшемуся
            Set<String> allOpenWindows = driver.getWindowHandles();
            allOpenWindows.remove(originalWindow);
            driver.switchTo().window(allOpenWindows.iterator().next());

            //закроем окно и вернемся назад к первому окну
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }
}
