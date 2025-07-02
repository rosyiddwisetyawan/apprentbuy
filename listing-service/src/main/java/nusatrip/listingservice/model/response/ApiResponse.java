package nusatrip.listingservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nusatrip.listingservice.model.Listing;

import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private boolean result;

    private Object listing;

    private Object listings;

    public ApiResponse(boolean result, Object listing) {
        this.result = result;
        this.listing = listing;
        this.listings = null;
    }

    public ApiResponse(boolean result, List<?> listings) {
        this.result = result;
        this.listing = null;
        this.listings = listings;
    }
}