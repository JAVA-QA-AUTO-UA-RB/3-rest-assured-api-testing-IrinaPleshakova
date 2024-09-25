import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class BaseTest {
    // Base URI for the Petstore API
    protected RequestSpecification requestSpec;

    @BeforeClass
    public void commonSetup() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/config.properties"));
        String baseUri = properties.getProperty("base.uri");

        RestAssured.filters(
                new RequestLoggingFilter(LogDetail.ALL), // Logs all details of the request
                new ResponseLoggingFilter(LogDetail.ALL) // Logs all details of the response
        );

        // Create a Request Specification
        requestSpec = given()
                .baseUri(baseUri)
                .header("Content-Type", "application/json");
    }
}
