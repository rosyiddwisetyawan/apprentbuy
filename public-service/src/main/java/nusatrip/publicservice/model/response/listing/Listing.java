package nusatrip.publicservice.model.response.listing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import nusatrip.publicservice.model.response.user.User;

@Data
public class Listing {
    private Long id;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("listing_type")
    private String listingType;
    private Integer price;
    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

    private User user;
}
