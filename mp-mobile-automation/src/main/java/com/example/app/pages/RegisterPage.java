package com.example.app.pages;

import com.example.app.base.BasePageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.io.FileWriter;
import java.io.IOException;

public class RegisterPage extends BasePageObject {

    private String email;

    By registerTitle() {
        return By.xpath("//android.view.View[@content-desc=\"Register\"]");
    }

    By fullnameField() {
        return By.xpath("//android.widget.EditText[1]");
    }

    By emailField() {
        return By.xpath("//android.widget.EditText[2]");
    }

    By passwordField() {
        return By.xpath("//android.widget.EditText[3]");
    }

    By registerButton() {
        return By.xpath("//android.widget.Button[@content-desc=\"Register\"]");
    }

    By requiredMessage(String message) {
        return By.xpath("//android.view.View[@content-desc=\"" + message + "\"]");
    }

    By errorMessage() {
        return By.xpath("//android.view.View[@content-desc=\"Gagal :(\"]");
    }

    public void validateOnRegisterPage() throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertTrue(isDisplayed(registerTitle()));
//        Assertions.assertEquals("Register", getText(registerTitle()));
    }

    public void inputFullname(String fullname) throws InterruptedException {
        click(fullnameField());
        Thread.sleep(1000);
        sendKeys(fullnameField(), fullname);
    }

    public void inputEmail(String email) throws InterruptedException {
        click(emailField());
        if (email.equals("new")) {
            this.email = randomEmail();
            try (FileWriter file = new FileWriter("src//test//resources//filejson//email.json")){
                file.write(this.email);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.email = email;
        }
        sendKeys(emailField(), this.email);
    }

    public void inputPassword(String password) throws InterruptedException {
        click(passwordField());
        sendKeys(passwordField(), password);
    }

    public void clickRegisterButton() {
        click(registerButton());
    }

    public void verifyRequiredMessage(String message) throws InterruptedException {
        Thread.sleep(1000);
        if (message.equals("required fullname")) {
            Assertions.assertTrue(isDisplayed(requiredMessage("fullname can not empty")));
        } else if (message.equals("required email")) {
            Assertions.assertTrue(isDisplayed(requiredMessage("email can not empty")));
        } else {
            Assertions.assertTrue(isDisplayed(requiredMessage("password can not empty")));
        }
    }

    public void verifyErrorMessage() throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertTrue(isDisplayed(errorMessage()));
//        Assertions.assertEquals("Gagal :(", getText(errorMessage()));
    }
}
