package helpers.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
public class BookingDTO {
    @JsonProperty("bookingid")
    @Builder.Default
    private Integer bookingId = 1;

    @JsonProperty("booking")
    @Builder.Default
    private BookingDataDTO booking = BookingDataDTO.builder().build();
}


