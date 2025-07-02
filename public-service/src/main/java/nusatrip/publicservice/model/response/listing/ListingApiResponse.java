package nusatrip.publicservice.model.response.listing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListingApiResponse {
    private List<Listing> listings;
    private Listing listing;
    @JsonProperty("message")
    public Boolean getMessage() {
        return (listing == null) ? false : null;
    }
}