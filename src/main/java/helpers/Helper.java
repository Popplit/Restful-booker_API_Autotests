package helpers;

import helpers.auth.AuthStorage;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Helper extends Settings {
    public static ValidatableResponse postBookingResponse(HashMap<String, Object> body) {
        return
            given()
                .body(body)
            .when()
                .post(BOOKING)
            .then()
                .log().ifValidationFails(LogDetail.ALL);
    }

    public static ValidatableResponse putBookingResponse(Integer bookingId, HashMap<String, Object> body) {
        return
            given()
                .log().all()
                .header("Cookie", "token=" + AuthStorage.getInstance().getNewToken())
                .body(body)
            .when()
                .put(BOOKING + "/" + bookingId)
            .then()
                .log().body();
    }

    public static ValidatableResponse patchBookingResponse(Integer bookingId, HashMap<String, Object> body) {
        return
            given()
                .log().all()
                .header("Cookie", "token=" + AuthStorage.getInstance().getNewToken())
                .body(body)
            .when()
                .patch(BOOKING + "/" + bookingId)
            .then()
                .log().ifValidationFails();
    }

    public static ValidatableResponse getBookingResponse(Integer bookingId) {
        return
            given()
            .when()
                .get(BOOKING + "/" + bookingId)
            .then()
                .log().ifValidationFails(LogDetail.ALL);
    }

    public static ValidatableResponse getBookingIdsByFirstAndLastName(String firstName, String lastName) {
        queryParameters.put("firstname", firstName);
        queryParameters.put("lastname", lastName);
        return
            given()
                .queryParams(queryParameters)
            .when()
                .get(BOOKING)
            .then()
                .log().all();
    }

    public static ValidatableResponse getBookingIdsByCheckIn(String checkIn) {
        queryParameters.put("checkin", checkIn);
        return
            given()
                .queryParams(queryParameters)
            .when()
                .get(BOOKING)
            .then()
                .log().all();
    }

    public static ValidatableResponse deleteBookingWithToken(Integer bookingId) {
        return
            given()
                .header("Cookie", "token=" + AuthStorage.getInstance().getNewToken())
            .when()
                .delete(BOOKING + "/" + bookingId)
            .then()
                .log().ifValidationFails(LogDetail.ALL);
    }

    public static ValidatableResponse deleteBookingWithoutToken(Integer bookingId) {
        return
            given()
            .when()
                .delete(BOOKING + "/" + bookingId)
            .then()
                .log().ifValidationFails(LogDetail.ALL);
    }
}
