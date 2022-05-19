package starter.categories;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static Utils.General.base_url;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Get {

    @Step("I set an endpoint for GET all categories")
    public String setAnEndpointForGetAllCategories() {
        return base_url + "/categories";
    }

    @Step("I request GET detail category")
    public void requestGetDetailCategory() {
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .when().get(setAnEndpointForGetAllCategories());
    }

    @Step("I validate the status code for GET all categories is {int}")
    public void validateTheStatusCodeForGetAllCategories(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details")
    public void validateTheDataDetails() {
        SerenityRest.then().body("data[0].ID", equalTo(getID()));
        SerenityRest.then().body("data[0].Name", equalTo(getName()));
    }

    @Step("get category ID from the response")
    public int getID() {
        Response response = SerenityRest.lastResponse();
        int id = response.body().path("data[0].ID");
        System.out.println("ID: " + id);
        return id;
    }

    @Step("get category name from the response")
    public String getName() {
        Response response = SerenityRest.lastResponse();
        String name = response.body().path("data[0].Name");
        System.out.println("Name: " + name);
        return name;
    }
}
