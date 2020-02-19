package testcases;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Tc002GetSingleEmployeeRecord extends BaseTest {

    @BeforeClass
    public void getSingleEmployee() throws InterruptedException {
        logger.info("******** Started Tc002GetSingleEmployeeRecord ********");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employee/" + empID);

        Thread.sleep(3000);
    }

    @Test
    public void checkResponseBody() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains(empID));
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
        Assert.assertEquals(contentType, "text/html; charset=UTF-8");
    }

    @Test
    public void checkServerType() {
        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "nginx/1.16.0");
    }

    @Test
    public void checkContentEncoding() {
        String contentEncoding = response.header("Content-Encoding");
        Assert.assertEquals(contentEncoding, "gzip");
    }

    @Test
    public void checkContentLength() {
        String contentLength =response.header("Content-Length");
        Assert.assertTrue(Integer.parseInt(contentLength) < 1500);
    }

    @AfterClass
    public void tearDown() {
        logger.info("******** Finishing Tc002GetSingleEmployeeRecord ********");
    }
}
