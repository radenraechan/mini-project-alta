package starter.product;

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

    private String name;
    private int price;

    @Step("I set an endpoint for POST new product")
    public String setAnEndpointForPostNewProduct() {
        return base_url + "/products";
    }

    @Step("I request POST detail product with input name and price")
    public void requestPostDetailProduct(String name, String price) {
        if (name.equals("")) {
            this.name = "";
        } else {
            this.name = name;
        }
        if (price.equals("")) {
            this.price = 0;
        } else {
            this.price = Integer.parseInt(price);
        }

        JSONObject requestData = new JSONObject();
        requestData.put("name", this.name);
        requestData.put("price", this.price);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestData.toJSONString())
                .when().post(setAnEndpointForPostNewProduct());
    }

    @Step("I validate the status code for POST new product is {int}")
    public void validateTheStatusCodeForPostNewProduct(int statusCode) {
        SerenityRest.then().statusCode(equalTo(statusCode));
    }

    @Step("validate the data details and message after create new product")
    public void validateTheDataDetailsAfterCreateNewProduct(String message) {
        if (message.equals("success")) {
            SerenityRest.then().body("data.ID", equalTo(getID()));
            SerenityRest.then().body("data.Name", equalTo(this.name));
            SerenityRest.then().body("data.Price", equalTo(this.price));
        } else {
            SerenityRest.then().body("error", equalTo(getErrorMessage()));
        }
    }

    @Step("get product id from the response")
    public int getID() {
        Response response = SerenityRest.lastResponse();
        int productId = response.body().path("data.ID");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//productId.json")) {
            file.write(productId);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Product ID: " + productId);
        return productId;
    }

    @Step("get error message from the response")
    public String getErrorMessage() {
        Response response = SerenityRest.lastResponse();
        String errorMessage = response.body().path("error");
        System.out.println("Error: " + errorMessage);
        return errorMessage;
    }
}
