package ru.stqa.training.selenium.PageObjects.Application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.training.selenium.PageObjects.Pages.CartPage;
import ru.stqa.training.selenium.PageObjects.Pages.MainPage;
import ru.stqa.training.selenium.PageObjects.Pages.ProductPage;

public class Application {
    private WebDriver driver;

    public CartPage cartPage;
    public MainPage mainPage;
    public ProductPage productPage;

    public Application() {
        driver = new FirefoxDriver();
        cartPage = new CartPage(driver);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
    }

    public void addDucksInCart() {
        mainPage.openMainPage();
        mainPage.chooseAndAddProduct();

    }
    public void removeAllDucks(){
        cartPage.goToCart();
        cartPage.removeAll();
    }

    public void quit() {
        driver.quit();
    }
}
