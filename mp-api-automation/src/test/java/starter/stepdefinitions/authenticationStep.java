package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.authentication.PostRegister;
import starter.authentication.PostLogin;

public class authenticationStep {

    @Steps
    PostRegister postRegister;

    @Steps
    PostLogin postLogin;

    @Given("I set an endpoint for POST new user")
    public void iSetAnEndpointForPOSTNewUser() {
        postRegister.setPostEndpoint();
    }

    @When("I request POST detail user with input {string}, {string} and {string}")
    public void iRequestPOSTDetailUserWithInputAnd(String fullname, String email, String password) {
        postRegister.requestPostDetailUser(fullname, email, password);
    }

    @Then("I validate the status code is {int}")
    public void iValidateTheStatusCodeIsStatusCode(int statusCode) {
        postRegister.validateStatusCode(statusCode);
        postLogin.validateStatusCode(statusCode);
    }

    @And("validate the data details and {string} after create user")
    public void validateTheDataDetailsAndAfterCreateUser(String message) {
        postRegister.validateDataDetail(message);
    }

    @Given("I set an endpoint for POST login user")
    public void iSetAnEndpointForPOSTLoginUser() {
        postLogin.setPostEndpointLoginUser();
    }

    @When("I request POST login user {string} and {string}")
    public void iRequestPOSTLoginUserAnd(String email, String password) throws Exception {
        postLogin.requestPostLoginUser(email, password);
    }

    @And("validate the data details and {string} after login user")
    public void validateTheDataDetailsAndAfterLoginUser(String message) {
        postLogin.validateDataDetailAfterLoginUser(message);
    }
}
