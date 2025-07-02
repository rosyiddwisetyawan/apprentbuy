package nusatrip.publicservice.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "Name is required")
    private String name;
}
