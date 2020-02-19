package APItestsuitedemo;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tc005ValidatingJsonResponse {
    @Test
    public void getWeatherDetails() {
        //specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        //request object
        RequestSpecification httpRequest = RestAssured.given();

        //response object
        Response response = httpRequest.request(Method.GET, "/medellin");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is :" + responseBody);
        Assert.assertEquals(responseBody.contains("Medellin"), true);
    }
}
