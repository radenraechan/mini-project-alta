package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.transactions.Get;
import starter.transactions.Post;

import java.io.IOException;

public class transactionSteps {

    @Steps
    Get get;

    @Steps
    Post post;

    @Given("I set an endpoint for GET all transactions")
    public void iSetAnEndpointForGETAllTransactions() {
        get.setAnEndpointForGetAllTransactions();
    }

    @When("I request GET detail transaction with {string} and {string}")
    public void iRequestGETDetailTransactionWith(String status, String statusToken) throws IOException {
        get.requestGetDetailTransactions(status, statusToken);
    }

    @Then("I validate the status code for GET all transactions is {int}")
    public void iValidateTheStatusCodeForGETAllTransactionsIs(int statusCode) {
        get.validateTheStatusCodeForGetAllTransactions(statusCode);
    }

    @And("validate the {string} data details of transaction")
    public void validateTheDataDetailsOfTransaction(String message) {
        get.validateTheMessageAndDataDetails(message);
    }

    @Given("I set an endpoint for POST new transaction")
    public void iSetAnEndpointForPOSTNewTransaction() {
        post.setAnEndpointForPostNewTransaction();
    }

    @When("I request POST detail transaction with {string}, {string}, input {string} and {string}")
    public void iRequestPOSTDetailTransactionWithInputAnd(String status, String statusToken, String productId, String quantity) throws IOException {
        post.requestPostDetailTransaction(status, statusToken, productId, quantity);
    }

    @Then("I validate the status code for POST new transaction is {int}")
    public void iValidateTheStatusCodeForPOSTNewTransactionIsStatusCode(int statusCode) {
        post.validateTheStatusCodeForPostNewTransaction(statusCode);
    }

    @And("validate the data details and {string} after create new transaction")
    public void validateTheDataDetailsAndAfterCreateNewTransaction(String message) {
        post.validateTheDataDetails(message);
    }
}
