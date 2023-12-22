package helpers;

import constants.Constants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import logger.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Settings implements Constants.Endpoints {
    private static String URL = "https://restful-booker.herokuapp.com";
    protected static Response response;
    protected HashMap<String, Object> headers,
                                      body,
                                      params;
    protected static LinkedHashMap<String, Object> queryParameters;
    public static final AllureRestAssured ALLURE_REQUEST_RESPONSE_TEMPLATE = new AllureRestAssured()
        .setRequestTemplate("http-request.ftl")
        .setResponseTemplate("http-response.ftl");

    @BeforeAll
    public static void setUp() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setAccept("application/json")
            .setBaseUri(BOOKING)
            .addFilter(ALLURE_REQUEST_RESPONSE_TEMPLATE)
            .build();
    }
    @BeforeEach
    public void beforeEach() {
        response = null;

        headers         = new HashMap<>();
        body            = new HashMap<>();
        params          = new HashMap<>();
        queryParameters = new LinkedHashMap<>();

        Logger.LOG4J_LOGGER.info("----- NEW TEST STARTED -----");
    }
}
