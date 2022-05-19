package starter.transactions;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static Utils.General.base_url;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Post {

    private String token;
    private int productId, quantity;

    @Step("I set an endpoint for POST new transaction")
    public String setAnEndpointForPostNewTransaction() {
        return base_url + "/orders";
    }

    @Step("Build request data")
    public List<JSONObject> bodyData(){
        List<JSONObject> body = new ArrayList<>();

        JSONObject data = new JSONObject();
        data.put("product_id", this.productId);
        data.put("quantity", this.quantity);

        body.add(data);
        return body;
    }

    @Step("I request POST detail transaction with status, token, input token, productId and quantity")
    public void requestPostDetailTransaction(String status, String statusToken, String productId, String quantity) throws IOException {
        if (status.equals("authorized")) {
            if (statusToken.equals("valid")) {
                this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);
            } else if (statusToken.equals("invalid")) {
                this.token = "invalidToken";
            } else {
                this.token = "";
            }
            if (productId.equals("")) {
                this.productId = 0;
            } else {
                this.productId = Integer.parseInt(productId);
            }
            if (quantity.equals("")) {
                this.quantity = 0;
            } else {
                this.quantity = Integer.parseInt(quantity);
            }

            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer "+ this.token)
                    //.body(requestData.toJSONString())
                    .body(bodyData())
                    .when().post(setAnEndpointForPostNewTransaction());
        } else {
            SerenityRest.given()
                    .header("Content-Type", "application/json")
                    .when().post(setAnEndpointForPostNewTransaction());
        }
    }

    @Step("I validate the status code for POST new transaction is {int}")
    public void validateTheStatusCodeForPostNewTransaction(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the message and data details after create new transaction")
    public void validateTheDataDetails(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body("data[0].ID", equalTo(getId()));
            SerenityRest.then().body("data[0].Quantity", equalTo(getQuantity()));
        } else if (message.equals("not found")) {
            SerenityRest.then().body("data[0].Quantity", equalTo(getQuantity()));
        } else {
            SerenityRest.then().body("error", equalTo(getErrorMessage()));
        }
    }

    @Step("get transaction id from the response")
    public int getId() {
        Response response = SerenityRest.lastResponse();
        int id = response.body().path("data[0].ID");
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

    @Step("get quantity from the response")
    public int getQuantity() {
        Response response = SerenityRest.lastResponse();
        int quantity = response.body().path("data[0].Quantity");
        System.out.println("Quantity: " + quantity);
        return quantity;
    }
}
