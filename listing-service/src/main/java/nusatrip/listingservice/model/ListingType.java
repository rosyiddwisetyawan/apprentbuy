package nusatrip.listingservice.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ListingType {

    RENT,
    SALE;

    @JsonValue
    public String toLowerCase() {
        return name().toLowerCase();
    }

    public static boolean isValidType(String type) {
        for (ListingType listingType : values()) {
            if (listingType.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    public static ListingType fromString(String type) {
        for (ListingType listingType : values()) {
            if (listingType.name().equalsIgnoreCase(type)) {
                return listingType;
            }
        }
        throw new IllegalArgumentException("Invalid listing type: " + type);
    }
}
