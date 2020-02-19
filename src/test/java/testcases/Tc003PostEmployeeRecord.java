package testcases;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RestUtils;

public class Tc003PostEmployeeRecord extends BaseTest {

    String empName = RestUtils.empName();
    String empSalary = RestUtils.empSal();
    String empAge = RestUtils.empAge();

    @BeforeClass
    public void createEmployee() throws InterruptedException {
        logger.info("******** Started Tc003PostEmployeeRecord ********");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        //JSONObject is a class that represents a simple JSON. We can add key-value pairs using the put method
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", empName);
        requestParams.put("salary", empSalary);
        requestParams.put("age", empAge);

        //add a header stating the request body is a JSON
        httpRequest.header("Content-Type", "application/json");

        //add the Json to the body of the request
        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST, "/create");

        Thread.sleep(5000);
    }

    @Test
    public void checkResponseBody() {
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(empName), true);
        Assert.assertEquals(responseBody.contains(empSalary), true);
        Assert.assertEquals(responseBody.contains(empAge), true);
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
        logger.info("******** Finishing Tc003PostEmployeeRecord ********");
    }
}
