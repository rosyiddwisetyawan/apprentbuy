package nusatrip.publicservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nusatrip.publicservice.model.request.ListingRequest;
import nusatrip.publicservice.model.request.UserRequest;
import nusatrip.publicservice.model.response.ApiResponse;
import nusatrip.publicservice.model.response.listing.Listing;
import nusatrip.publicservice.model.response.listing.ListingApiResponse;
import nusatrip.publicservice.model.response.user.User;
import nusatrip.publicservice.model.response.user.UserApiResponse;
import nusatrip.publicservice.service.PublicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public-api")
@Validated
public class PublicController {

    private final PublicService publicService;

    public PublicController(PublicService publicService) {
        this.publicService = publicService;
    }

    @GetMapping("/listings")
    public ResponseEntity<ApiResponse> getListings(
            @RequestParam(name = "page_num", defaultValue = "1") int pageNum,
            @RequestParam(name = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(name = "user_id", required = false) Integer userId ) {
        ApiResponse response = publicService.getListings(pageNum, pageSize, userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/users", consumes = "application/json")
    public ResponseEntity<UserApiResponse> createUser(@Valid @RequestBody UserRequest request) {
        UserApiResponse response = publicService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/listings", consumes = "application/json")
    public ResponseEntity<ListingApiResponse> createListing(@Valid @RequestBody ListingRequest request) {
        ListingApiResponse response = publicService.createListing(request);
        return ResponseEntity.ok(response);
    }
}
