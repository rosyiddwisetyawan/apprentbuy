package nusatrip.publicservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ListingRequest {

    @JsonProperty("user_id")
    @NotNull(message = "User ID is required")
    private Long userId;

    @JsonProperty("listing_type")
    @NotBlank(message = "Listing type is required")
    private String listingType;

    @NotNull(message = "Price is required")
    private Integer price;
}
