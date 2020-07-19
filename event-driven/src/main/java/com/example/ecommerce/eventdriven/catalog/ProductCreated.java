package com.example.ecommerce.eventdriven.catalog;

import com.example.ecommerce.eventdriven.commons.events.DomainEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ProductCreated implements DomainEvent {

    UUID eventId = UUID.randomUUID();
    UUID productId;
    Instant when;

    ProductCreated(UUID productId) {
        this(productId, Instant.now());
    }

    @Override
    public UUID getAggregateId() {
        return productId;
    }
}
