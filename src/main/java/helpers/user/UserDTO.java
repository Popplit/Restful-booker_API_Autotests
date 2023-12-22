package helpers.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
public class UserDTO {
    @JsonProperty("username")
    @Builder.Default
    private String userName = "admin";

    @JsonProperty("password")
    @Builder.Default
    private String password = "password123";
}
