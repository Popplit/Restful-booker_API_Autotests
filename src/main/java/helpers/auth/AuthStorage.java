package helpers.auth;

import helpers.Settings;
import helpers.user.UserDTO;

import static io.restassured.RestAssured.given;

public class AuthStorage extends Settings {

    private static volatile AuthStorage instance;

    public  AuthStorage() { }

    public static AuthStorage getInstance() {
        if (instance == null) {
            instance = new AuthStorage();
        }
        return instance;
    }

    public String getNewToken() {
        return
            given()
                .body(UserDTO.builder().build())
                .log().all()
            .when()
                .post(AUTH)
            .then().extract().jsonPath().getString("token");
    }

}
