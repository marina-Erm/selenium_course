package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

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

        //Проход по пунктам меню
        driver.findElement(By.xpath("//*[contains(@href,'doc=template')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-template']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-logotype']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=catalog')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-catalog']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-product_groups']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-option_groups']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-manufacturers']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-suppliers']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-delivery_statuses']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-sold_out_statuses']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-quantity_units']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-csv']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=countries')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=currencies')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=customers')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-customers']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-csv']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-newsletter']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=geo_zones')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=languages')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-languages']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-storage_encoding']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=jobs')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-jobs']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-customer']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-shipping']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-payment']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-order_total']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-order_success']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-order_action']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=orders')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-orders']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-order_statuses']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=pages')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=monthly_sales')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-monthly_sales']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-most_sold_products']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-most_shopping_customers']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=store_info')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-store_info']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-defaults']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-general']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-listings']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-images']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-checkout']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-advanced']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-security']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=slides')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=tax_classes')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-tax_classes']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-tax_rates']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=search')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-search']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-scan']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-csv']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=users')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[contains(@href,'doc=vqmods')]")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

        driver.findElement(By.xpath("//*[@id='doc-vqmods']")).click();
        wait.until(presenceOfElementLocated(By.tagName("h1")));

    }
}
