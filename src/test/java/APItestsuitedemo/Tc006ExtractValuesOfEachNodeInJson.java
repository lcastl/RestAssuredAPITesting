package APItestsuitedemo;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tc006ExtractValuesOfEachNodeInJson {
    @Test
    public void getWeatherDetails() {
        //specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        //request object
        RequestSpecification httpRequest = RestAssured.given();

        //response object
        Response response = httpRequest.request(Method.GET, "/medellin");

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.get("City"));
        System.out.println(jsonPath.get("Temperature"));
        System.out.println(jsonPath.get("Humidity"));
        System.out.println(jsonPath.get("WeatherDescription"));
        System.out.println(jsonPath.get("WindSpeed"));
        System.out.println(jsonPath.get("WindDirectionDegree"));
        Assert.assertEquals(jsonPath.get("Temperature"), "24.38 Degree celsius");
    }
}
