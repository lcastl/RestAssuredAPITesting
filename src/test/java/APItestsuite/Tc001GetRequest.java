package APItestsuite;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tc001GetRequest {

    @Test
    public void getWeatherDetails() {
        //specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        //request object
        RequestSpecification httpRequest = RestAssured.given();

        //response object
        Response response = httpRequest.request(Method.GET, "/Medellin");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is :" + responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        //status line validation
        String statusLine = response.getStatusLine();
        System.out.println("status line is: " + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }
}
