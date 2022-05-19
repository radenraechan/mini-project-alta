package starter.product;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import static Utils.General.base_url;
import static org.hamcrest.Matchers.*;

public class GetById {

    private int id;

    @Step("I set an endpoint for GET product by id")
    public String setAnEndpointForGetProductById() {
        return base_url + "/products/{id}";
    }

    @Step("I request GET detail product")
    public void requestGetDetailProduct(String id) {
        if (id.equals("")) {
            this.id = 0;
        } else {
            this.id = Integer.parseInt(id);
        }

        JSONObject requestData = new JSONObject();
        requestData.put("id", this.id);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("id", this.id)
                .body(requestData.toJSONString())
                .when().get(setAnEndpointForGetProductById());
    }

    @Step("I validate the status code for GET product by id is {int}")
    public void validateTheStatusCodeForGetProductById(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details")
    public void validateTheDataDetails(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body("data.ID", equalTo(this.id));
            SerenityRest.then().body("data.Name", equalTo(getName()));
            SerenityRest.then().body("data.Price", equalTo(getPrice()));
            SerenityRest.then().body("data.Ratings", equalTo(getRatings()));
        } else {
            SerenityRest.then().body("error", equalTo(getErrorMessage()));
        }
    }

    @Step("get product name from the response")
    public String getName() {
        Response response = SerenityRest.lastResponse();
        String name = response.body().path("data.Name");
        System.out.println("Name: " + name);
        return name;
    }

    @Step("get price from the response")
    public int getPrice() {
        Response response = SerenityRest.lastResponse();
        int price = response.body().path("data.Price");
        System.out.println("Price: " + price);
        return price;
    }

    @Step("get ratings from the response")
    public int getRatings() {
        Response response = SerenityRest.lastResponse();
        int ratings = response.body().path("data.Ratings");
        System.out.println("Ratings: " + ratings);
        return ratings;
    }

    @Step("get error message from the response")
    public String getErrorMessage() {
        Response response = SerenityRest.lastResponse();
        String errorMessage = response.body().path("error");
        System.out.println("Error: " + errorMessage);
        return errorMessage;
    }
}
