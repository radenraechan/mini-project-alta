package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.comments.Get;
import starter.comments.Post;

import java.io.IOException;

public class commentStep {

    @Steps
    Get get;

    @Steps
    Post post;

    @Given("I set an endpoint for GET comments")
    public void iSetAnEndpointForGETComments() {
        get.setAnEndpointForGetComments();
    }

    @When("I request GET detail comments product with input {string}")
    public void iRequestGETDetailCommentsProductWithInput(String id) {
        get.requestGetDetailComments(id);
    }

    @Then("I validate the status code for GET comments product is {int}")
    public void iValidateTheStatusCodeForGETCommentsProductIsStatusCode(int statusCode) {
        get.validateTheStatusCode(statusCode);
    }

    @And("validate the data details and {string} for get comments")
    public void validateTheDataDetailsAndForGetComments(String message) {
        get.validateTheDataDetails(message);
    }

    @Given("I set an endpoint for POST comments")
    public void iSetAnEndpointForPOSTComments() {
        post.setAnEndpointForPostComments();
    }

    @When("I request POST detail comments product with {string}, {string}, input {string} and {string}")
    public void iRequestPOSTDetailCommentsProductWithInputAnd(String status, String statusToken, String id, String content) throws IOException {
        post.requestPostDetail(status, statusToken, id, content);
    }

    @Then("I validate the status code for POST comments product is {int}")
    public void iValidateTheStatusCodeForPOSTCommentsProductIsStatusCode(int statusCode) {
        post.validateTheStatusCode(statusCode);
    }

    @And("validate the data details and {string} for post comments")
    public void validateTheDataDetailsAndForPostComments(String message) {
        post.validateTheDataDetails(message);
    }
}
