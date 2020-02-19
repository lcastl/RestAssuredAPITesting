package testcases;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Tc001GetAllEmployees extends BaseTest {

    @BeforeClass
    public void getAllEmployees() throws InterruptedException {
        logger.info("******** Started Tc001GetAllEmployees ********");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employees");

        Thread.sleep(3000);
    }

    @Test
    public void checkResponseBody() {
        logger.info("******** Checking response body ********");
        String responseBody = response.getBody().asString();
        logger.info("Response Body==>" + responseBody);
        Assert.assertNotNull(responseBody);
    }

    @Test
    public void checkStatusCode() {
        logger.info("******** Checking response body ********");
        int statusCode = response.getStatusCode();
        logger.info("Status Code==>" + statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void checkResponseTime() {
        logger.info("******** Checking Response Time ********");
        long responseTime = response.getTime();
        logger.info("Response Time is =>" + responseTime);

        if (responseTime > 2020) {
            logger.warn("Response Time is greater than 2000");
        }
        Assert.assertTrue(responseTime < 2020);
    }

    @Test
    public void checkStatusLine() {
        logger.info("******** Checking Status Line ********");
        String statusLine = response.getStatusLine();
        logger.info("Status line is =>" + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    public void checkContentType() {
        logger.info("******** Checking Content Type ********");
        String contentType = response.header("Content-Type");
        logger.info("Content Type is =>" + contentType);
        Assert.assertEquals(contentType, "application/json;charset=utf-8");
    }

    @Test
    public void checkServerType() {
        logger.info("******** Checking server type ********");
        String serverType = response.header("Server");
        logger.info("Server Type is =>" + serverType);
        Assert.assertEquals(serverType, "nginx/1.16.0");
    }

    @Test
    public void checkContentEncoding() {
        logger.info("******** Checking content encoding ********");
        String contentEncoding = response.header("Content-Encoding");
        logger.info("Content Encoding is =>" + contentEncoding);
        Assert.assertEquals(contentEncoding, "gzip");
    }

    @Test
    public void checkContentLength() {
        logger.info("******** Checking content length ********");
        String contentLength =response.header("Content-Length");
        logger.info("Content Length is =>" + contentLength);
        if (Integer.parseInt(contentLength) < 100) {
            logger.warn("Content length is less than 100");
        }
        Assert.assertTrue(Integer.parseInt(contentLength) > 100);
    }

    @Test
    public void checkCookies() {
        logger.info("******** Checking cookies ********");
        String cookie = response.getCookie("PHPSESSID");
        //Assert.assertEquals(cookie, "464c83601c58ecd77510b3f2318101e5");
    }

    @AfterClass
    public void tearDown() {
        logger.info("******** Finishing Tc001GetAllEmployees ********");
    }
}