package nusatrip.publicservice.service;

import lombok.RequiredArgsConstructor;
import nusatrip.publicservice.model.request.ListingRequest;
import nusatrip.publicservice.model.request.UserRequest;
import nusatrip.publicservice.model.response.ApiResponse;
import nusatrip.publicservice.model.response.listing.Listing;
import nusatrip.publicservice.model.response.listing.ListingApiResponse;
import nusatrip.publicservice.model.response.user.User;
import nusatrip.publicservice.model.response.user.UserApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class PublicService {

    private final RestTemplate restTemplate;
    @Value("${listing.service.url}")
    private String listingServiceUrl;
    @Value("${user.service.url}")
    private String userServiceUrl;

    public ApiResponse getListings(int pageNum, int pageSize, Integer userId) {
        String url = listingServiceUrl + "?page_num=" + pageNum + "&page_size=" + pageSize;
        if (userId != null) {
            url += "&user_id=" + userId;    // listing sudah ter‑filter untuk user ini
        }

        ResponseEntity<ListingApiResponse> listingsResponse =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );

        List<Listing> listings = listingsResponse.getBody().getListings();

        User userCache = null;
        if (userId != null) {
            userCache = fetchUser(userId.longValue());   // metode helper di bawah
        }

        for (Listing listing : listings) {
            Long userIdFromListing = listing.getUserId();

            if (userIdFromListing == null) {
                listing.setUser(null);
                continue;
            }

            if (userId != null && userIdFromListing.equals(userId.longValue())) {
                listing.setUser(userCache);
            } else {
                listing.setUser(fetchUser(userIdFromListing));
            }
        }

        return new ApiResponse(true, listings);
    }

    private User fetchUser(Long uid) {
        try {
            String userUrl = userServiceUrl +"/"+  uid;
            ResponseEntity<UserApiResponse> resp = restTemplate.exchange(
                    userUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<UserApiResponse>() {}
            );
            return resp.getBody().getUser();
        } catch (Exception e) {
            return null;
        }
    }

    public UserApiResponse createUser(UserRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String url = UriComponentsBuilder.fromHttpUrl(userServiceUrl)
                .queryParam("name", request.getName())
                .toUriString();

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<UserApiResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<UserApiResponse>() {}
        );

        return response.getBody();
    }

    public ListingApiResponse createListing(ListingRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String url = UriComponentsBuilder.fromHttpUrl(listingServiceUrl)
                .queryParam("user_id", request.getUserId())
                .queryParam("listing_type", request.getListingType())
                .queryParam("price", request.getPrice())
                .toUriString();

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<ListingApiResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ListingApiResponse>() {}
        );

        return response.getBody();
    }
}
