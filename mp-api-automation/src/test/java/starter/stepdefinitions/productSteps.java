package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.product.Get;
import starter.product.GetById;
import starter.product.Post;

public class productSteps {

    @Steps
    Get get;

    @Steps
    GetById getById;

    @Steps
    Post post;

    @Given("I set an endpoint for GET all products")
    public void iSetAnEndpointForGETAllProducts() {
        get.setAnEndpointForGetAllProducts();
    }

    @When("I request GET detail product")
    public void iRequestGETDetailProduct() {
        get.requestGetDetailProduct();
    }

    @Then("I validate the status code for GET all products is {int}")
    public void iValidateTheStatusCodeForGETAllProductsIs(int statusCode) {
        get.validateTheStatusCodeForGetAllProducts(statusCode);
    }

    @And("validate the data details for get product")
    public void validateTheDataDetailsForGetProduct() {
        get.validateTheDataDetails();
    }

    @Given("I set an endpoint for GET product by id is {string}")
    public void iSetAnEndpointForGETProductByIdIs(String id) {
        getById.setAnEndpointForGetProductById();
    }

    @When("I request GET detail product with input {string}")
    public void iRequestGETDetailProductWithInput(String id) {
        getById.requestGetDetailProduct(id);
    }

    @Then("I validate the status code for GET product by id is {int}")
    public void iValidateTheStatusCodeForGETProductByIdIsStatusCode(int statusCode) {
        getById.validateTheStatusCodeForGetProductById(statusCode);
    }

    @And("validate the data details and {string} details")
    public void validateTheDataDetailsAndDetails(String message) {
        getById.validateTheDataDetails(message);
    }

    @Given("I set an endpoint for POST new product")
    public void iSetAnEndpointForPOSTNewProduct() {
        post.setAnEndpointForPostNewProduct();
    }

    @When("I request POST detail product with input {string} and {string}")
    public void iRequestPOSTDetailProductWithInputAnd(String name, String price) {
        post.requestPostDetailProduct(name, price);
    }

    @Then("I validate the status code for POST new product is {int}")
    public void iValidateTheStatusCodeForPOSTNewProductIs(int statusCode) {
        post.validateTheStatusCodeForPostNewProduct(statusCode);
    }

    @And("validate the data details and {string} after create new product")
    public void validateTheDataDetailsAndAfterCreateNewProduct(String message) {
        post.validateTheDataDetailsAfterCreateNewProduct(message);
    }
}
