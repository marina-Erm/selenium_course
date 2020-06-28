package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LoginInLitecartAdmin {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
        driver = new FirefoxDriver(options);
      //driver= new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void login() throws InterruptedException {
        driver.get("http://localhost/litecart/public_html/admin/login.php");
        Thread.sleep(1000);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.tagName("button")).click();
        wait.until(titleIs("My Store"));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
