package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.ratings.Get;
import starter.ratings.Post;

import java.io.IOException;

public class ratingStep {

    @Steps
    Get get;

    @Steps
    Post post;

    @Given("I set an endpoint for GET ratings")
    public void iSetAnEndpointForGETRatings() {
        get.setAnEndpointForGetRatings();
    }

    @When("I request GET detail ratings product with input {string}")
    public void iRequestGETDetailRatingsProductWithInput(String id) {
        get.requestGetDetailProduct(id);
    }

    @Then("I validate the status code for GET ratings product is {int}")
    public void iValidateTheStatusCodeForGETRatingsProductIsStatusCode(int statusCode) {
        get.validateTheStatusCode(statusCode);
    }

    @And("validate the data details and {string} for get ratings")
    public void validateTheDataDetailsAndForGetRatings(String message) {
        get.validateTheDataDetails(message);
    }

    @Given("I set an endpoint for POST ratings")
    public void iSetAnEndpointForPOSTRatings() {
        post.setAnEndpointForPostRatings();
    }

    @When("I request POST detail ratings product with {string}, {string}, input {string} and {string}")
    public void iRequestPOSTDetailRatingsProductWithInputAnd(String status, String statusToken, String id, String count) throws IOException {
        post.requestPostDetail(status, statusToken, id, count);
    }

    @Then("I validate the status code for POST ratings product is {int}")
    public void iValidateTheStatusCodeForPOSTRatingsProductIsStatusCode(int statusCode) {
        post.validateTheStatusCode(statusCode);
    }

    @And("validate the data details and {string} for post ratings")
    public void validateTheDataDetailsAndForPostRatings(String message) {
        post.validateTheDataDetails(message);
    }
}
