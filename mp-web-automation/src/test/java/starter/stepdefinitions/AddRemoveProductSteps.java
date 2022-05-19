package starter.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.pages.CartPage;
import starter.pages.ProductPage;

public class AddRemoveProductSteps {

    @Steps
    ProductPage productPage;

    @Steps
    CartPage cartPage;

    @When("I click buy button of product")
    public void iClickBuyButtonOfProduct() {
        productPage.clickBuyButton("1");
    }

    @When("I click add button")
    public void iClickAddButton() {
        cartPage.clickAddButton();
    }

    @Then("I can see detail of my order")
    public void iCanSeeDetailOfMyOrder() {
        cartPage.productNameLabelAppears();
        cartPage.quantityLabelAppears();
        cartPage.costLabelAppears();
    }

    @When("I click remove button")
    public void iClickRemoveButton() {
        cartPage.clickRemoveButton();
    }

    @Then("I will recive card message that {string}")
    public void iWillReciveCardMessageThat(String message) {
        cartPage.iconMessageAppears();
        cartPage.iconMessageTextEqual(message);
    }
}