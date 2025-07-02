package nusatrip.publicservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private boolean result;

    private Object listings;

    private Object messages;

    public ApiResponse(boolean result, List<?> listings) {
        this.result = result;
        this.listings = listings;
    }

    // Constructor untuk response dengan message
    public ApiResponse(boolean result, String messages) {
        this.result = result;
        this.messages = messages;
    }


}
