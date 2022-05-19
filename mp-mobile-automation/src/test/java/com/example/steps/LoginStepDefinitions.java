package com.example.steps;

import com.example.app.pages.LoginPage;
import com.example.app.pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {

    ProductPage productPage = new ProductPage();
    LoginPage loginPage = new LoginPage();

    @Given("I am on login page")
    public void iAmOnLoginPage() {
        loginPage.validateOnLoginPage();
    }

    @When("I input {string} and {string}")
    public void iInputAnd(String email, String password) throws InterruptedException {
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
    }

    @And("click login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("validate the {string} after login")
    public void validateTheAfterLogin(String message) throws InterruptedException {
        if (message.equals("success")) {
            productPage.validateOnProductPage();
        } else if (message.equals("not existed")) {
            loginPage.verifyErrorMessage();
        } else {
            loginPage.verifyRequiredMessage(message);
        }
    }
}