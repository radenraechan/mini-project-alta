package starter.comments;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static Utils.General.base_url;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Get {

    private String id;

    @Step("I set an endpoint for GET comments")
    public String setAnEndpointForGetComments() {
        return base_url + "/products/{id}/comments";
    }

    @Step("I request GET detail comments product with input id")
    public void requestGetDetailComments(String id) {
        if (id.equals("")) {
            this.id = " ";
        } else {
            this.id = id;
        }

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("id", this.id)
                .when().get(setAnEndpointForGetComments());
    }

    @Step("I validate the status code for GET comments product is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details and message for get comments")
    public void validateTheDataDetails(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body("data[0].ID", equalTo(getId()));
            SerenityRest.then().body("data[0].Content", equalTo(getComments()));
        } else if (message.equals("not existed")) {
            SerenityRest.then().body("data", notNullValue());
        } else if (message.equals("error")){
            SerenityRest.then().body("error", equalTo(getErrorMessage()));
        } else {
            SerenityRest.then().body(equalTo("not found\n"));
        }
    }

    @Step("get id from the response")
    public int getId() {
        Response response = SerenityRest.lastResponse();
        int id = response.body().path("data[0].ID");
        System.out.println("ID: " + id);
        return id;
    }

    @Step("get comments from the response")
    public String getComments() {
        Response response = SerenityRest.lastResponse();
        String comments = response.body().path("data[0].Content");
        System.out.println("Comments: " + comments);
        return comments;
    }

    @Step("get error message from the response")
    public String getErrorMessage() {
        Response response = SerenityRest.lastResponse();
        String errorMessage = response.body().path("error");
        System.out.println("Error: " + errorMessage);
        return errorMessage;
    }
}
