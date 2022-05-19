package starter.ratings;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


import static Utils.General.base_url;
import static org.hamcrest.Matchers.*;

public class Get {

    private String id;

    @Step("I set an endpoint for GET ratings")
    public String setAnEndpointForGetRatings() {
        return base_url + "/products/{id}/ratings";
    }

    @Step("I request GET detail ratings product with input id")
    public void requestGetDetailProduct(String id) {
        if (id.equals("")) {
            this.id = " ";
        } else {
            this.id = id;
        }

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("id", this.id)
                .when().get(setAnEndpointForGetRatings());
    }

    @Step("I validate the status code for GET ratings product is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details and message for get ratings")
    public void validateTheDataDetails(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body("data", equalTo(getRatings()));
        } else if (message.equals("not existed")) {
            SerenityRest.then().body("data", equalTo(getRatings()));
        } else if (message.equals("error")){
            SerenityRest.then().body("error", equalTo(getErrorMessage()));
        } else {
            SerenityRest.then().body(equalTo("not found\n"));
        }
    }

    @Step("get ratings from the response")
    public int getRatings() {
        Response response = SerenityRest.lastResponse();
        int rating = response.body().path("data");
        System.out.println("Rating: " + rating);
        return rating;
    }

    @Step("get error message from the response")
    public String getErrorMessage() {
        Response response = SerenityRest.lastResponse();
        String errorMessage = response.body().path("error");
        System.out.println("Error: " + errorMessage);
        return errorMessage;
    }
}
