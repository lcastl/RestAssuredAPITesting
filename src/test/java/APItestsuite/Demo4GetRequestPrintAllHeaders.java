package APItestsuite;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class Demo4GetRequestPrintAllHeaders {

    @Test
    public void getWeatherDetails() {
        //specify base URI
        RestAssured.baseURI = "https://maps.googleapis.com";

        //request object
        RequestSpecification httpRequest = RestAssured.given();

        //response object
        Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is :" + responseBody);

        //print all the headers
        Headers allHeaders = response.headers(); //capture all the headers from response

        for (Header header: allHeaders) {
            System.out.println(header.getName() + "   " + header.getValue());
        }
    }
}
