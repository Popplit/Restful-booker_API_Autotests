package helpers.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
public class BookingDatesDTO {
    @JsonProperty("checkin")
    @Builder.Default
    private String checkIn = "2023-01-01";

    @JsonProperty("checkout")
    @Builder.Default
    private String checkOut = "2023-01-01";
}
