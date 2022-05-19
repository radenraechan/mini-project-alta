package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.pages.LoginPage;
import starter.pages.ProductPage;

public class LogoutSteps {

    @Steps
    ProductPage productPage;

    @Steps
    LoginPage loginPage;

    @When("I click user icon")
    public void iClickUserIcon() {
        productPage.clickUserIcon();
    }

    @And("click logout button")
    public void clickLogoutButton() {
        productPage.clickLogoutButton();
    }

    @Then("I success logout")
    public void iSuccessLogout() {
        loginPage.validateOnLoginPage();
    }
}
