package booking;

import helpers.Helper;
import helpers.Settings;
import helpers.booking.BookingDatesDTO;
import io.qameta.allure.Description;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.*;

import static constants.Constants.Schemes.POST_BOOKING_SCHEME;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingNegativeTest extends Settings {

    private static Integer createdBookingId;

    @Test
    @Order(1)
    @Description("Создание бронирования с неверным типом данных у имени и фамилии")
    @DisplayName("POST " + BOOKING + " code 400")
    void postBookingWithWrongTypeFirstAndLastNameTest() {
        body.put("firstname", 1);
        body.put("lastname", 2);
        body.put("totalprice", 1050);
        body.put("depositpaid", true);
        body.put("bookingdates", BookingDatesDTO.builder()
            .checkIn("2023-12-13")
            .checkOut("2023-12-26")
            .build());
        body.put("additionalneeds", "Breakfast");

        Helper.postBookingResponse(body)
            .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @Order(2)
    @Description("Создание бронирования с неверным типом данных у итоговой стоимости")
    @DisplayName("PATCH " + BOOKING + " code 200")
    void patchBookingWithWrongTypeTotalPriceTest() {
        body.put("firstname", "Alexander");
        body.put("lastname", "Atamanjuk");
        body.put("totalprice", 1050);
        body.put("depositpaid", true);
        body.put("bookingdates", BookingDatesDTO.builder()
            .checkIn("2023-12-13")
            .checkOut("2023-12-26")
            .build());
        body.put("additionalneeds", "Breakfast");

        createdBookingId = Helper.postBookingResponse(body)
            .statusCode(SC_OK)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(POST_BOOKING_SCHEME))
            .extract().jsonPath().getInt("bookingid");

        body.clear();
        body.put("totalprice", "free");

        Helper.patchBookingResponse(createdBookingId, body)
            .statusCode(SC_OK)
            .body("totalprice", equalTo(null));
    }

    @Test
    @Order(3)
    @Description("Удаление бронирования без токена")
    @DisplayName("DELETE " + BOOKING + " code 403")
    void deleteBookingTest() {
        Helper.deleteBookingWithoutToken(createdBookingId)
            .statusCode(SC_FORBIDDEN);
    }
}
