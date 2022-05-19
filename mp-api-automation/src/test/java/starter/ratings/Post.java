package starter.ratings;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static Utils.General.base_url;
import static org.hamcrest.Matchers.equalTo;

public class Post {

    private String token, id;
    private int count;

    @Step("I set an endpoint for POST ratings")
    public String setAnEndpointForPostRatings() {
        return base_url + "/products/{id}/ratings";
    }

    @Step("I request POST detail ratings product with statusAuthorized, statusToken, input id and count")
    public void requestPostDetail(String status, String statusToken, String id, String count) throws IOException {
        if (status.equals("authorized")) {
            if (statusToken.equals("valid")) {
                this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
            } else if (statusToken.equals("invalid")){
                this.token = "invalidToken";
            } else {
                this.token = "";
            }

            if (id.equals("")) {
                this.id = " ";
            } else {
                this.id = id;
            }

            if (count.equals("")) {
                this.count = 0;
            } else {
                this.count = Integer.parseInt(count);
            }

            JSONObject requestData = new JSONObject();
            requestData.put("count", this.count);

            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer "+ this.token)
                    .pathParam("id", this.id)
                    .body(requestData.toJSONString())
                    .when().post(setAnEndpointForPostRatings());
        } else {
            if (id.equals("")) {
                this.id = " ";
            } else {
                this.id = id;
            }

            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("id", this.id)
                    .when().post(setAnEndpointForPostRatings());
        }
    }

    @Step("I validate the status code for POST ratings product is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details and message for post ratings")
    public void validateTheDataDetails(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body("data.ID", equalTo(getId()));
            SerenityRest.then().body("data.Ratings", equalTo(getRatings()));
        } else if (message.equals("not found")){
            SerenityRest.then().body(equalTo("not found\n"));
        } else {
            SerenityRest.then().body("error", equalTo(getErrorMessage()));
        }
    }

    @Step("get id from the response")
    public int getId() {
        Response response = SerenityRest.lastResponse();
        int id = response.body().path("data.ID");
        System.out.println("ID: " + id);
        return id;
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
