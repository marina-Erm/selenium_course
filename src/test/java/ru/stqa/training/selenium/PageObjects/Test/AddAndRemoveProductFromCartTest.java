package ru.stqa.training.selenium.PageObjects.Test;

import org.junit.Assert;
import org.junit.Test;


public class AddAndRemoveProductFromCartTest extends NewTestBase{


    @Test
    public void addAndRemoveProductFromCart() {
     app.addDucksInCart();
     app.removeAllDucks();

     Assert.assertTrue(app.cartPage.cartIsEmpty());
    }










}
