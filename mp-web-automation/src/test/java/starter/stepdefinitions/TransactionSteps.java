package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import starter.pages.CartPage;
import starter.pages.LoginPage;
import starter.pages.TransactionPage;

public class TransactionSteps {

    @Steps
    LoginPage loginPage;

    @Steps
    CartPage cartPage;

    @Steps
    TransactionPage transactionPage;

    @And("click add button")
    public void clickAddButton() {
        cartPage.clickAddButton();
    }

    @And("click pay button")
    public void clickPayButton() {
        cartPage.clickPayButton();
    }

    @And("I have logged in")
    public void iHaveLoggedIn() throws InterruptedException {
        loginPage.inputEmail("email@gmail.com");
        loginPage.inputPassword("pass123*");
        loginPage.clickLoginButton();
        Thread.sleep(3000);
    }

    @Then("I will direct to transaction page")
    public void iWillDirectToTransactionPage() {
        transactionPage.headerAppears();
        transactionPage.headerTextEqual();
    }

    @And("I can see detail of my transaction")
    public void iCanSeeDetailOfMyTransaction() {
    }

    @And("validate on transaction page")
    public void validateOnTransactionPage() {
        transactionPage.validateOnTransactionPage();
    }
}