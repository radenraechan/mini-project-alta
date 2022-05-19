package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.pages.DetailProductPage;
import starter.pages.ProductPage;

public class DetailProductSteps {

    @Steps
    ProductPage productPage;

    @Steps
    DetailProductPage detailProductPage;

    @When("I click {string} detail button")
    public void iClickId_productDetailButton(String id_product) {
        productPage.clickDetailButton(id_product);
    }

    @Then("I will direct to detail product page")
    public void iWillDirectToDetailProductPage() {
        detailProductPage.headerAppears();
    }

    @And("validate {string} on detail product page")
    public void validateOnDetailProductPage(String product_name) {
        detailProductPage.headerTextEqual(product_name);
        detailProductPage.validateOnDetailProductPage();
    }
}