package nusatrip.userservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonProperty("created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
    private Instant updatedAt;


    public long getCreatedAt() {
        return createdAt != null ? createdAt.toEpochMilli() * 1000 : 0;
    }

    public long getUpdatedAt() {
        return updatedAt != null ? updatedAt.toEpochMilli() * 1000 : 0;
    }

}
