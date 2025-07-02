package nusatrip.userservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private boolean result;

    private Object user;

    private Object users;

    public ApiResponse(boolean result, Object user) {
        this.result = result;
        this.user = user;
        this.users = null;
    }

    public ApiResponse(boolean result, List<?> users) {
        this.result = result;
        this.user = null;
        this.users = users;
    }


}
