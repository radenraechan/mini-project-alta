package starter.authentication;

import Utils.General;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static Utils.General.base_url;
import static org.hamcrest.Matchers.equalTo;

public class PostLogin {

    private General general = new General();
    private String email, password;

    @Step("I set an endpoint for POST login user")
    public String setPostEndpointLoginUser(){
        return base_url + "/auth/login";
    }

    @Step("I request POST login user")
    public void requestPostLoginUser(String email, String password) throws Exception {
        if (email.equals("existed")) {
            this.email = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//email.json"), StandardCharsets.UTF_8);
        } else if (email.equals("not existed")) {
            this.email = general.randomEmail();
        } else {
            this.email = "";
        }
        this.password = password;

        JSONObject requestData = new JSONObject();
        requestData.put("email", this.email);
        requestData.put("password", this.password);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestData.toJSONString())
                .when().post(setPostEndpointLoginUser());
    }

    @Step("I validate the status code is {int}")
    public void validateStatusCode(int statusCode){
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data detail after login user")
    public void validateDataDetailAfterLoginUser(String message){
        if (message.equals("success")) {
            SerenityRest.then().body("data", equalTo(getToken()));
        } else {
            SerenityRest.then().body("error", equalTo(message));
        }
    }

    @Step("get token from the response")
    public String getToken() {
        Response response = SerenityRest.lastResponse();
        String token = response.body().path("data");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//token.json")) {
            file.write(token);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Token: " + token);
        return token;
    }
}
