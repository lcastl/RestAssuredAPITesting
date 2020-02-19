package datadriventestingdemo;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.XLUtils;

import java.io.IOException;

public class DataDrivenTestAddNewEmployees {

    @Test(dataProvider = "empDataProvider")
    public void postNewEmployees(String eName, String eAge, String eSal) {
        //specify base URI
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

        //request object
        RequestSpecification httpRequest = RestAssured.given();

        //request payload sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", eName);
        requestParams.put("salary", eSal);
        requestParams.put("age", eAge);

        //add a header stating the request body is a JSON
        httpRequest.header("Content-Type", "application/json");

        //add the Json to the body of the request
        httpRequest.body(requestParams.toJSONString());

        //POST REQUEST
        Response response = httpRequest.request(Method.POST, "/create");

        //capture response body to perform validations
        String responseBody = response.getBody().asString();
        System.out.println("Response body is " + responseBody);
        Assert.assertEquals(responseBody.contains(eName), true);
        Assert.assertEquals(responseBody.contains(eSal), true);
        Assert.assertEquals(responseBody.contains(eAge), true);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
    }

    @DataProvider(name = "empDataProvider")
    public Object[][] getEmployeeData() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/java/utils/empdata.xlsx";
        int rowCount = XLUtils.getRowCount(path, "Sheet1");
        int colCount = XLUtils.getCellCount(path, "Sheet1", 1);
        String empData[][] = new String[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                empData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
            }
        }
        return (empData);
    }
}
