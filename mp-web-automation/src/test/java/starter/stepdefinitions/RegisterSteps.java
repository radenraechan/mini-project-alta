package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.pages.ProductPage;
import starter.pages.LoginPage;
import starter.pages.RegisterPage;

public class RegisterSteps {

    @Steps
    ProductPage productPage;

    @Steps
    LoginPage loginPage;

    @Steps
    RegisterPage registerPage;

//    @And("click link register")
//    public void clickLinkRegister() {
//        loginPage.headerAppears();
//        loginPage.headerTextEqual();
//        loginPage.validateOnLoginPage();
//        loginPage.clickLinkRegister();
//    }

    @Given("I am on register page")
    public void iAmOnRegisterPage() {
        registerPage.openUrl();
        registerPage.headerAppears();
        registerPage.headerTextEqual();
    }

    @When("I input {string} fullName")
    public void iInputFullName(String fullName) {
        registerPage.inputFullName(fullName);
    }

    @And("input {string} email")
    public void inputEmail(String email) {
        registerPage.inputEmail(email);
    }

    @And("input {string} password")
    public void inputPassword(String password) {
        registerPage.inputPassword(password);
    }

    @And("click register button")
    public void clickRegisterButton() {
        registerPage.clickRegisterButton();
    }

    @And("double click register button")
    public void doubleClickRegisterButton() {
        registerPage.clickRegisterButton();
        registerPage.clickRegisterButton();
    }

    @Then("I get the {string} and {string} after register")
    public void iGetTheAndAfterRegister(String result, String text) {
        if(result.equals("icon warning")){
            registerPage.iconWarningAppears();
            registerPage.textWarningEquals(text);
        } else if (result.equals("direct to login page")) {
            loginPage.headerAppears();
            loginPage.headerTextEqual();
        } else {
            registerPage.iconWarningAppears();
        }
    }
}