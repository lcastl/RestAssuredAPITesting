package base;

        import io.restassured.response.Response;
        import io.restassured.specification.RequestSpecification;
        import org.apache.log4j.Level;
        import org.apache.log4j.Logger;
        import org.apache.log4j.PropertyConfigurator;
        import org.testng.annotations.BeforeClass;

public class BaseTest {

    public static RequestSpecification httpRequest;
    public static Response response;
    public static String empID = "23"; //Hard coded - input for get details of single employee & update employee

    public Logger logger;

    @BeforeClass
    public void setup() {
        logger = Logger.getLogger("EmployeesRestAPI"); //added Logger
        PropertyConfigurator.configure("log4j.properties"); //added logger
        logger.setLevel(Level.DEBUG);
    }
}
