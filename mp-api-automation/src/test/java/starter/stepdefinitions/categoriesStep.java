package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.categories.Get;
import starter.categories.Post;

public class categoriesStep {

    @Steps
    Get get;

    @Steps
    Post post;

    @Given("I set an endpoint for GET all categories")
    public void iSetAnEndpointForGETAllCategories() {
        get.setAnEndpointForGetAllCategories();
    }

    @When("I request GET detail category")
    public void iRequestGETDetailCategory() {
        get.requestGetDetailCategory();
    }

    @Then("I validate the status code for GET all categories is {int}")
    public void iValidateTheStatusCodeForGETAllCategoriesIs(int statusCode) {
        get.validateTheStatusCodeForGetAllCategories(statusCode);
    }

    @And("validate the data details")
    public void validateTheDataDetails() {
        get.validateTheDataDetails();
    }

    @Given("I set an endpoint for POST new category")
    public void iSetAnEndpointForPOSTNewCategory() {
        post.setAnEndpointForPostNewCategory();
    }

    @When("I request POST detail category with input name is {string}")
    public void iRequestPOSTDetailCategoryWithInputNameIs(String name) {
        post.requestPostDetailCategory(name);
    }

    @Then("I validate the status code for POST new category is {int}")
    public void iValidateTheStatusCodeForPOSTNewCategoryIs(int statusCode) {
        post.validateTheStatusCodeForPostNewCategory(statusCode);
    }

    @And("validate the data details and {string} message after create new category")
    public void validateTheDataDetailsAndMessageAfterCreateNewCategory(String message) {
        post.validateTheDataDetailsAfterCreateNewCategory(message);
    }
}
