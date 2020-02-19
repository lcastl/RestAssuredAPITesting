package testcases;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Tc005DeleteEmployeeRecord extends BaseTest {

    @BeforeClass
    public void deleteEmployee() throws InterruptedException {
        logger.info("******** Started Tc005DeleteEmployeeRecord ********");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        response = httpRequest.request(Method.GET, "/employees");

        //First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        //Capture id
        String empID = jsonPathEvaluator.get("[0].id");
        response = httpRequest.request(Method.DELETE, "/delete/" + empID);

        Thread.sleep(3000);
    }

    @Test
    public void checkResponseBody() {
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
    }

    @Test
    public void checkStatusCode() {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void checkResponseTime() {
        long responseTime = response.getTime();
        Assert.assertTrue(responseTime < 2000);
    }

    @Test
    public void checkStatusLine() {
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    public void checkContentType() {
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json;charset=utf-8");
    }

    @Test
    public void checkServerType() {
        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "nginx/1.16.0");
    }

    @Test
    public void checkContentLength() {
        String contentLength =response.header("Content-Length");
        Assert.assertTrue(Integer.parseInt(contentLength) < 1500);
    }

    @AfterClass
    public void tearDown() {
        logger.info("******** Finishing Tc004PutEmployeeRecord ********");
    }
}
