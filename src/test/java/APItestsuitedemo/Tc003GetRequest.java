package APItestsuitedemo;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tc003GetRequest {
    @Test
    public void googleMapTest() {
        //specify base URI
        RestAssured.baseURI = "https://maps.googleapis.com";

        //request object
        RequestSpecification httpRequest = RestAssured.given();

        //response object
        Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is :" + responseBody);

        //validating headers
        String contentType = response.header("Content-Type");
        System.out.println("content type is " + contentType);
        Assert.assertEquals(contentType, "application/xml; charset=UTF-8");

        String contentEncoding = response.header("Content-Encoding");
        System.out.println("content encoding is " + contentEncoding);
        Assert.assertEquals(contentEncoding, "gzip");
    }
}
