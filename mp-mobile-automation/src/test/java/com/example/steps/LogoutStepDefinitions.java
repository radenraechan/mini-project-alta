package com.example.steps;

import com.example.app.pages.LoginPage;
import com.example.app.pages.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogoutStepDefinitions {

    ProductPage productPage = new ProductPage();
    LoginPage loginPage = new LoginPage();

    @Given("I have logged in")
    public void iHaveLoggedIn() throws InterruptedException {
        loginPage.validateOnLoginPage();
        loginPage.inputEmail("email123@gmail.com");
        loginPage.inputPassword("pass123*");
        loginPage.clickLoginButton();
    }

    @When("I click logout button")
    public void iClickLogoutButton() throws InterruptedException {
        productPage.validateOnProductPage();
        Thread.sleep(1000);
        productPage.clickUserIcon();
    }

    @Then("validate success logout")
    public void validateSuccessLogout() throws InterruptedException {
        productPage.validateOnProductPage();
    }
}