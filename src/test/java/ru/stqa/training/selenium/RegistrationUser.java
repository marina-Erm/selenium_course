package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class RegistrationUser extends TestBase {


    @Test
    public void registrationUser() {
        //перейдем на главную страницу ИМ  и затем на форму регистрации
        driver.get("http://localhost/litecart/public_html/en/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(titleIs("Online Store | My Store"));

        driver.findElement(By.xpath("//*[@id='box-account-login']//td/a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(titleIs("Create Account | My Store"));

        //Подготовим данные для ввода
        String firstname = "Test1";
        String lastname = "Test2";
        String address = "Gorkogo street, 1";
        String postcode = "12345";
        String city = "New York";
        String country = "United States";
        String email = String.valueOf(System.currentTimeMillis()) + "@test.com";
        String phone = "+79101234567";
        String password = "Qwerty1";

        //теперь заполним формы
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstname);
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(address);
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys(postcode);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(phone);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);

        //выберем страну из списка
        WebElement countrySelector = driver.findElement(By.xpath("//span[@class='selection']/span"));
        new Actions(driver).moveToElement(countrySelector)
                .click()
                .sendKeys(country)
                .sendKeys(Keys.ENTER)
                .perform();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //снимем флажок из чек-бокса если он установлен, чтобы не получать рассылки
        WebElement checkBox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        if (checkBox.getAttribute("checked") != null) checkBox.click();

        //жмем кнопку зарегистрироваться
        driver.findElement(By.xpath("//button[@name='create_account']")).click();

        //убеждаемся, что нас перенеправило на главную страницу
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(titleIs("Online Store | My Store"));

        //выйдем из учетки
        logOut();

        //залогинимся под созданной учеткой
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='login']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(titleIs("Online Store | My Store"));

        //снова выйдем
        logOut();
    }

    //выйход из учетки
    public void logOut() {
        driver.findElement(By.xpath("//*[@id='box-account']//ul//a[contains(@href,'logout')]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(titleIs("Online Store | My Store"));
    }
}
