package hh.backend.littlefreelibrary.domain;

import java.time.Instant;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

@MappedSuperclass
public class BaseEntity {

    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }   

    public Instant getCreatedAt() {
        return createdAt;
    }
}
