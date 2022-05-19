package starter.categories;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import static Utils.General.base_url;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Post {

    private String categoryName;
    private int categoryId;

    @Step("I set an endpoint for POST new category")
    public String setAnEndpointForPostNewCategory() {
        return base_url + "/categories";
    }

    @Step("I request POST detail category with input name")
    public void requestPostDetailCategory(String categoryName) {
        if (categoryName.equals("null")) {
            this.categoryName = "";
        } else {
            this.categoryName = categoryName;
        }

        JSONObject requestData = new JSONObject();
        requestData.put("name", this.categoryName);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestData.toJSONString())
                .when().post(setAnEndpointForPostNewCategory());
    }

    @Step("I validate the status code for POST new category is {int}")
    public void validateTheStatusCodeForPostNewCategory(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details after create new category")
    public void validateTheDataDetailsAfterCreateNewCategory(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body("data.ID", equalTo(getID()));
            SerenityRest.then().body("data.Name", equalTo(getName()));
        } else {
            SerenityRest.then().body("error", equalTo(getErrorMessage()));
        }
    }

    @Step("get category ID from the response")
    public int getID() {
        Response response = SerenityRest.lastResponse();
        int categoryID = response.body().path("data.ID");
        System.out.println("Category ID: " + categoryID);
        return categoryID;
    }

    @Step("get category name from the response")
    public String getName() {
        Response response = SerenityRest.lastResponse();
        String categoryName = response.body().path("data.Name");
        System.out.println("Name: " + categoryName);
        return categoryName;
    }

    @Step("get error message from the response")
    public String getErrorMessage() {
        Response response = SerenityRest.lastResponse();
        String errorMessage = response.body().path("error");
        System.out.println("Error: " + errorMessage);
        return errorMessage;
    }
}
