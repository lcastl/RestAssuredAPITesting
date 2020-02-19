package APItestsuitedemo;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tc002PostRequest {
    @Test
    public void registrationSuccessful() {
        //specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";

        //request object
        RequestSpecification httpRequest = RestAssured.given();

        //request payload sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Luiss");
        requestParams.put("LastName", "Castellanoss");
        requestParams.put("UserName", "lcastellanoss");
        requestParams.put("Password", "lcastellxcvb");
        requestParams.put("Email", "lcastellanosas@hotmail.com");

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());

        //response object
        Response response = httpRequest.request(Method.POST, "/register");

        //print response
        String responseBody = response.getBody().asString(); //attach above data to the request
        System.out.println("Response Body is :" + responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);

        //success code validation
        String successCode = response.jsonPath().get("SuccessCode");
        System.out.println("success code is: " + successCode);
        Assert.assertEquals(successCode, "OPERATION_SUCCESS");
    }
}