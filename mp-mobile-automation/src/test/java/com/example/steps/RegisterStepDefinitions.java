package com.example.steps;

import com.example.app.pages.LoginPage;
import com.example.app.pages.ProductPage;
import com.example.app.pages.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterStepDefinitions {

    ProductPage productPage = new ProductPage();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();

    @Given("I am on product page")
    public void iAmOnProductPage() throws InterruptedException {
        productPage.validateOnProductPage();
    }

    @When("I click user icon")
    public void iClickUserIcon() throws InterruptedException {
        productPage.clickUserIcon();
    }

    @And("click register link")
    public void clickRegisterLink() throws InterruptedException {
        loginPage.validateOnLoginPage();
        loginPage.clickRegisterLink();
    }

    @Given("I am on register page")
    public void iAmOnRegisterPage() throws InterruptedException {
        registerPage.validateOnRegisterPage();
    }

    @When("I input {string}, {string}, and {string}")
    public void iInputAnd(String fullname, String email, String password) throws InterruptedException {
        registerPage.inputFullname(fullname);
        registerPage.inputEmail(email);
        registerPage.inputPassword(password);
    }

    @And("click register button")
    public void clickRegisterButton() {
        registerPage.clickRegisterButton();
    }

    @Then("validate the {string}")
    public void validateThe(String message) throws InterruptedException {
        if (message.equals("success")) {
            productPage.validateOnProductPage();
        } else if (message.equals("existed")) {
            registerPage.verifyErrorMessage();
        } else {
            registerPage.verifyRequiredMessage(message);
        }
    }
}
