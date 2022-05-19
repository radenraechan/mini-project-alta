package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.pages.CartPage;
import starter.pages.ProductPage;

public class OrderSteps {

    @Steps
    ProductPage productPage;

    @Steps
    CartPage cartPage;

    @Given("I am on product page")
    public void iAmOnProductPage() {
        productPage.openUrl();
        productPage.headerAppears();
        productPage.headerTextEqual();
        productPage.validateOnProductPage();
    }

    @When("I click {string} buy button")
    public void iClickBuyButtonOf(String id_product) {
        productPage.clickBuyButton(id_product);
    }

    @And("click cart button")
    public void clickCartButton() {
        productPage.clickCartButton();
    }

    @Then("validate {string} on cart page")
    public void validateOnCartPage(String product_name) {
        cartPage.productNameLabelAppears();
        cartPage.productNameLabelTextEqual(product_name);
        cartPage.quantityLabelAppears();
        cartPage.costLabelAppears();
    }
}