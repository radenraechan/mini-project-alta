package starter.transactions;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static Utils.General.base_url;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Get {

    private String token;

    @Step("I set an endpoint for GET all transactions")
    public String setAnEndpointForGetAllTransactions() {
        return base_url + "/orders";
    }

    @Step("I request GET detail transaction")
    public void requestGetDetailTransactions(String status, String statusToken) throws IOException {
        if (status.equals("authorized")) {
            if (statusToken.equals("valid token")) {
                this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
            } else if (statusToken.equals("invalid token")) {
                this.token = "invalidToken";
            } else {
                this.token = "";
            }

            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.token)
                    .when().get(setAnEndpointForGetAllTransactions());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .when().get(setAnEndpointForGetAllTransactions());
        }
    }

    @Step("I validate the status code for GET all transactions is {int}")
    public void validateTheStatusCodeForGetAllTransactions(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the message and data details")
    public void validateTheMessageAndDataDetails(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body(notNullValue());
        } else {
            SerenityRest.then().body("error", equalTo(getErrorMessage()));
        }
    }

    @Step("get error message from the response")
    public String getErrorMessage() {
        Response response = SerenityRest.lastResponse();
        String errorMessage = response.body().path("error");
        System.out.println("Error: " + errorMessage);
        return errorMessage;
    }
}
