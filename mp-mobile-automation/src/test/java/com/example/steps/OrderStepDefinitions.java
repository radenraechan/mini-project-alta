package com.example.steps;

import com.example.app.pages.ProductPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderStepDefinitions {

    ProductPage productPage = new ProductPage();

    @When("I click {int} buy button on {int} product")
    public void iClickQuantityBuyButtonOnIdProduct(int quantity, int id) {
        if (id > 0) {
            for (int i = 0; i < quantity; i++)
            {
                productPage.clickBuyButton(id);
                id++;
            }
        }
    }

    @Then("validate the {string} and {int} of cart icon")
    public void validateTheAndQuantityOfCartIcon(String message, int quantity) throws InterruptedException {
        if (message.equals("failed")) {
            productPage.verifyCartIcon(0);
        } else {
            productPage.verifyCartIcon(quantity);
        }
    }
}