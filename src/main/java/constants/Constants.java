package constants;

public interface Constants {
    public interface Endpoints {
        String
            BASE_URL = "https://restful-booker.herokuapp.com",
            AUTH     = BASE_URL + "/auth",
            BOOKING  = BASE_URL + "/booking";
    }
    public interface Schemes {
        String
            PATH_SCHEMES        = "responseSchemes",
            GET_BOOKING_SCHEME  = PATH_SCHEMES + "/getBooking.json",
            POST_BOOKING_SCHEME = PATH_SCHEMES + "/postBooking.json";

    }
}
