package nusatrip.publicservice.model.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserApiResponse {
    private User user;
    @JsonProperty("message")
    public Boolean getMessage() {
        return (user == null) ? false : null;
    }
}