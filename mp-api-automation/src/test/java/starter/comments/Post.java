package starter.comments;

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

    private String token, id, content;

    @Step("I set an endpoint for POST comments")
    public String setAnEndpointForPostComments() {
        return base_url + "/products/{id}/comments";
    }

    @Step("I request POST detail comments product with statusAuthorized, statusToken, input id and content")
    public void requestPostDetail(String status, String statusToken, String id, String content) throws IOException {
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

            this.content = content;

            JSONObject requestData = new JSONObject();
            requestData.put("content", this.content);

            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer "+ this.token)
                    .pathParam("id", this.id)
                    .body(requestData.toJSONString())
                    .when().post(setAnEndpointForPostComments());
        } else {
            if (id.equals("")) {
                this.id = " ";
            } else {
                this.id = id;
            }

            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .pathParam("id", this.id)
                    .when().post(setAnEndpointForPostComments());
        }
    }

    @Step("I validate the status code for POST comments product is {int}")
    public void validateTheStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details and message for post comments")
    public void validateTheDataDetails(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body("data.ID", equalTo(getId()));
            SerenityRest.then().body("data.Content", equalTo(getComments()));
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

    @Step("get comments from the response")
    public String getComments() {
        Response response = SerenityRest.lastResponse();
        String  comments = response.body().path("data.Content");
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
