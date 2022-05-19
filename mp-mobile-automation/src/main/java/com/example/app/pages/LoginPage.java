package com.example.app.pages;

import com.example.app.base.BasePageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class LoginPage extends BasePageObject {

    By loginTitle() {
        return By.xpath("//android.view.View[@content-desc=\"Login\"]");
    }

    By emailField() {
        return By.xpath("//android.widget.EditText[1]");
    }

    By passwordField() {
        return By.xpath("//android.widget.EditText[2]");
    }

    By loginButton() {
        return By.xpath("//android.widget.Button[@content-desc=\"Login\"]");
    }

    By registerLink() {
        return By.xpath("//android.widget.Button[@content-desc=\"Register\"]");
    }

    By requiredMessage(String message) {
        return By.xpath("//android.view.View[@content-desc=\"" + message + "\"]");
    }

    By errorMessage() {
        return By.xpath("//android.view.View[@content-desc=\"Email atau password tidak valid.\"]");
    }

    public void validateOnLoginPage() {
        Assertions.assertTrue(isDisplayed(loginTitle()));
//        Assertions.assertEquals("Login", getText(loginTitle()));
    }

    public void inputEmail(String email) throws InterruptedException {
        click(emailField());
        Thread.sleep(1000);
        sendKeys(emailField(), email);
    }

    public void inputPassword(String password) {
        click(passwordField());
        sendKeys(passwordField(), password);
    }

    public void clickLoginButton() {
        click(loginButton());
    }

    public void clickRegisterLink() {
        click(registerLink());
    }

    public void verifyRequiredMessage(String message) throws InterruptedException {
        Thread.sleep(1000);
        if (message.equals("required email")) {
            Assertions.assertTrue(isDisplayed(requiredMessage("email can not empty")));
        } else {
            Assertions.assertTrue(isDisplayed(requiredMessage("password can not empty")));
        }
    }

    public void verifyErrorMessage() throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertTrue(isDisplayed(errorMessage()));
//        Assertions.assertEquals("Email atau password tidak valid.", getText(errorMessage()));
    }
}
