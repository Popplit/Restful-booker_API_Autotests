package booking;

import helpers.Helper;
import helpers.Settings;
import helpers.booking.BookingDatesDTO;
import io.qameta.allure.Description;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.*;

import static constants.Constants.Schemes.GET_BOOKING_SCHEME;
import static constants.Constants.Schemes.POST_BOOKING_SCHEME;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingPositiveTest extends Settings {

    private static Integer createdBookingId;

    @Test
    @Order(1)
    @Description("Создание бронирования")
    @DisplayName("POST " + BOOKING + " code 200")
    void postBookingWithCorrectDataTest() {
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
    }

    @Test
    @Order(2)
    @Description("Получение данных бронирования")
    @DisplayName("GET " + BOOKING + " code 200")
    void getBookingTest() {
        Helper.getBookingResponse(createdBookingId)
            .statusCode(SC_OK)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_BOOKING_SCHEME))
            .body("firstname", equalTo("Alexander"))
            .body("lastname", equalTo("Atamanjuk"))
            .body("totalprice", equalTo(1050))
            .body("bookingdates.checkin", equalTo("2023-12-13"))
            .body("bookingdates.checkout", equalTo("2023-12-26"))
            .body("additionalneeds", equalTo("Breakfast"));
    }

    @Test
    @Order(3)
    @Description("Получение всех идентификаторов бронирования соответствующих фильтрации по дате въезда")
    @DisplayName("GET IDs by checkIn " + BOOKING + " code 200")
    void getBookingIdsByCheckInTest() {
        Helper.getBookingIdsByCheckIn("2023-12-12")
            .statusCode(SC_OK)
            .body("", hasItem(allOf(hasEntry("bookingid", createdBookingId))));
    }

    @Test
    @Order(4)
    @Description("Получение всех идентификаторов бронирования соответствующих фильтрации по имени и фамилии")
    @DisplayName("GET IDs by firstName and lastName " + BOOKING + " code 200")
    void getBookingIdsByFirstAndLastNameTest() {
        Helper.getBookingIdsByFirstAndLastName("Alexander", "Atamanjuk")
            .statusCode(SC_OK)
            .body("", hasItem(allOf(hasEntry("bookingid", createdBookingId))));
    }

    @Test
    @Order(5)
    @Description("Полное редактирование бронирования")
    @DisplayName("PUT " + BOOKING + " code 200")
    void putBookingWithCorrectDataTest() {
        body.put("firstname", "Test");
        body.put("lastname", "Testov");
        body.put("totalprice", 111);
        body.put("depositpaid", true);
        body.put("bookingdates", BookingDatesDTO.builder()
            .checkIn("2018-01-01")
            .checkOut("2019-01-01")
            .build());
        body.put("additionalneeds", "Breakfast");

        Helper.putBookingResponse(createdBookingId, body)
            .statusCode(SC_OK)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_BOOKING_SCHEME));
    }

    @Test
    @Order(6)
    @Description("Частичное редактирование параметры уплаты депозита бронирования")
    @DisplayName("PATCH " + BOOKING + " code 200")
    void patchBookingDepositPaidValueTest() {
        body.put("depositpaid", false);
        Helper.patchBookingResponse(createdBookingId, body)
            .statusCode(SC_OK)
            .assertThat()
            .body("depositpaid", equalTo(false))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_BOOKING_SCHEME));
    }

    @Test
    @Order(7)
    @Description("Удаление бронирования")
    @DisplayName("DELETE " + BOOKING + " code 201")
    void deleteBookingTest() {
        Helper.deleteBookingWithToken(createdBookingId)
            .statusCode(SC_CREATED);
    }
}
