package nusatrip.listingservice.controller;

import nusatrip.listingservice.model.Listing;
import nusatrip.listingservice.model.response.ApiResponse;
import nusatrip.listingservice.service.ListingService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listings")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllListings(
            @RequestParam(defaultValue = "1") int page_num,
            @RequestParam(defaultValue = "10") int page_size,
            @RequestParam(required = false) Integer user_id) {

        ApiResponse response = listingService.getAllListings(page_num, page_size, user_id);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<ApiResponse> createListing(
            @RequestParam("user_id") int userId,
            @RequestParam("listing_type") String listingType,
            @RequestParam("price") int price) {

        ApiResponse response = listingService.createListing(userId, listingType, price);
        return ResponseEntity.status(response.isResult() ? 201 : 400).body(response); // 201 jika berhasil, 400 jika gagal
    }
}
