package com.example.app.pages;

import com.example.app.base.BasePageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class ProductPage extends BasePageObject {

    By productTitle() {
        return By.xpath("//android.view.View[@content-desc=\"Products\"]");
    }

    By userIcon() {
        return By.xpath("//android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
    }

    By buyButton(int id) {
        return By.xpath("(//android.widget.Button[@content-desc=\"Beli\"])[" + id + "]");
    }

    By cartIcon(int product) {
        return By.xpath("//android.widget.Button[@content-desc=\"" + product + "\"]");
    }

    public void validateOnProductPage() throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertTrue(isDisplayed(productTitle()));
//        Assertions.assertEquals("Products", getText(productTitle()));
    }

    public void clickUserIcon() {
        click(userIcon());
    }

    public void clickBuyButton(int id) {
        click(buyButton(id));
    }

    public void verifyCartIcon(int product) throws InterruptedException {
        Thread.sleep(3000);
        Assertions.assertTrue(isDisplayed(cartIcon(product)));
    }
}
