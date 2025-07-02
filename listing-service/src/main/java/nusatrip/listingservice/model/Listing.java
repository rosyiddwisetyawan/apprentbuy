package nusatrip.listingservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_listings")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Listing ID (auto-generated)

    @Column(nullable = false)
    @JsonProperty("user_id")
    private int userId; // ID of the user who created the listing (required)

    @Column(nullable = false)
    private int price; // Price of the listing. Should be above zero (required)

    @Enumerated(EnumType.STRING) // Simpan sebagai string di database
    @Column(nullable = false)
    @JsonProperty("listing_type")
    private ListingType listingType;// Type of the listing. rent or sale (required)

    @CreationTimestamp
    @Column(updatable = false)
    @JsonProperty("created_at")
    private Instant createdAt; // Created at timestamp. In microseconds (auto-generated)

    @UpdateTimestamp
    @JsonProperty("updated_at")
    private Instant updatedAt; // Updated at timestamp. In microseconds (auto-generated)

    // Getter untuk konversi ke mikrodetik
    public long getCreatedAt() {
        return createdAt != null ? createdAt.toEpochMilli() * 1000 : 0;
    }

    public long getUpdatedAt() {
        return updatedAt != null ? updatedAt.toEpochMilli() * 1000 : 0;
    }
}