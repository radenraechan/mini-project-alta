package starter.product;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static Utils.General.base_url;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Get {

    @Step("I set an endpoint for GET all products")
    public String setAnEndpointForGetAllProducts() {
        return base_url + "/products";
    }

    @Step("I request GET detail product")
    public void requestGetDetailProduct() {
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .when().get(setAnEndpointForGetAllProducts());
    }

    @Step("I validate the status code for GET all products is {int}")
    public void validateTheStatusCodeForGetAllProducts(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details for get product")
    public void validateTheDataDetails() {
        SerenityRest.then().body("data[0]", notNullValue());
    }
}
