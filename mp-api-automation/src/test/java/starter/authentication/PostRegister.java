package starter.authentication;

import Utils.General;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import static Utils.General.base_url;
import static org.hamcrest.Matchers.equalTo;

public class PostRegister {

    private General general = new General();
    private String fullname, email, password;

    @Step("I set an endpoint for POST new user")
    public String setPostEndpoint(){
        return base_url + "/auth/register";
    }

    @Step("I request POST detail user with input fullname, email and password")
    public void requestPostDetailUser(String fullname, String email, String password){
        if (email.equals("new")){
            this.email = general.randomEmail();
            try (FileWriter file = new FileWriter("src//test//resources//filejson//email.json")){
                file.write(this.email);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (email.equals("same")){
            this.email = "email@gmail.com";
        } else {
            this.email = "";
        }
        this.fullname = fullname;
        this.password = password;

        JSONObject requestData = new JSONObject();
        requestData.put("fullname", this.fullname);
        requestData.put("email", this.email);
        requestData.put("password", this.password);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestData.toJSONString())
                .when().post(setPostEndpoint());
    }

    @Step("I validate the status code is {int}")
    public void validateStatusCode(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail and message after create user")
    public void validateDataDetail(String message){
        if (message.equals("success")) {
            SerenityRest.then().body("data.ID", equalTo(getID()));
            SerenityRest.then().body("data.Fullname", equalTo(this.fullname));
            SerenityRest.then().body("data.Email", equalTo(this.email));
            SerenityRest.then().body("data.Password", equalTo(this.password));
        } else if (message.equals("existed")){
            SerenityRest.then().body("error", equalTo(getErrorMessage()));
        } else {
            SerenityRest.then().body("error", equalTo(message));
        }
    }

    @Step("get id from the response")
    public int getID() {
        Response response = SerenityRest.lastResponse();
        int id = response.body().path("data.ID");
        System.out.println("ID: " + id);
        return id;
    }

    @Step("get error message from the response")
    public String getErrorMessage() {
        Response response = SerenityRest.lastResponse();
        String errorMessage = response.body().path("error");
        System.out.println("Error: " + errorMessage);
        return errorMessage;
    }
}
