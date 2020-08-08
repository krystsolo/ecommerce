package com.example.ecommerce.eventdriven.inventory;

import com.example.ecommerce.eventdriven.commons.events.DomainEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public interface StockEvent extends DomainEvent {

    UUID getProductId();

    default UUID getAggregateId() {
        return getProductId();
    }

    @Value
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    class InsufficientStockIssued implements StockEvent {

        UUID eventId = UUID.randomUUID();
        UUID productId;
        Instant when;
        long amount;

        InsufficientStockIssued(UUID productId, long amount) {
            this(productId, Instant.now(), amount);
        }
    }

    @Value
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    class ItemTakenFromStock implements StockEvent {

        UUID eventId = UUID.randomUUID();
        UUID productId;
        Instant when;
        long amount;

        ItemTakenFromStock(UUID productId, long amount) {
            this(productId, Instant.now(), amount);
        }
    }
}

