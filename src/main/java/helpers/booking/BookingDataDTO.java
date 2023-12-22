package helpers.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
public class BookingDataDTO {
    @JsonProperty("firstname")
    @Builder.Default
    private String firstName = "Firstname";

    @JsonProperty("lastname")
    @Builder.Default
    private String lastName = "Lastname";

    @JsonProperty("totalprice")
    @Builder.Default
    private Integer totalPrice = 0;

    @JsonProperty("depositpaid")
    @Builder.Default
    private Boolean depositPaid = false;

    @JsonProperty("bookingdates")
    @Builder.Default
    private BookingDatesDTO bookingDates = BookingDatesDTO.builder().build();

    @JsonProperty("additionalneeds")
    @Builder.Default
    private String additionalNeeds = "none";
}


