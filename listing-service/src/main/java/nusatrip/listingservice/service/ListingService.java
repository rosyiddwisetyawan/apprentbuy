package nusatrip.listingservice.service;

import nusatrip.listingservice.model.Listing;
import nusatrip.listingservice.model.ListingType;
import nusatrip.listingservice.model.response.ApiResponse;
import nusatrip.listingservice.repository.ListingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {

    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public ApiResponse getAllListings(int pageNum, int pageSize, Integer userId) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        List<Listing> listings = userId != null
                ? listingRepository.findByUserId(userId, pageRequest).getContent()
                : listingRepository.findAll(pageRequest).getContent();

        return new ApiResponse(true, listings);
    }

    public ApiResponse createListing(int userId, String listingType, int price) {
        if (price <= 0 || !ListingType.isValidType(listingType)) {
            return new ApiResponse(false, "Invalid listing type or price");
        }

        ListingType type = ListingType.fromString(listingType);

        Listing newListing = new Listing();
        newListing.setUserId(userId);
        newListing.setPrice(price);
        newListing.setListingType(type);

        Listing savedListing = listingRepository.save(newListing);
        return new ApiResponse(true, savedListing);
    }

}
