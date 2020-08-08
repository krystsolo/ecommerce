package com.example.ecommerce.eventdriven.order;

import com.example.ecommerce.eventdriven.commons.events.DomainEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@AllArgsConstructor(access = AccessLevel.NONE)
public class OrderCompleted implements DomainEvent {

    UUID eventId = UUID.randomUUID();
    UUID orderId;
    Instant when = Instant.now();
    List<OrderedProduct> orderedProducts;

    OrderCompleted(UUID orderId, List<OrderItem> orderedItems) {
        this.orderId = orderId;
        this.orderedProducts = orderedItems.stream()
                .map(orderItem -> new OrderedProduct(orderItem.getProductId(), orderItem.getAmount()))
                .collect(Collectors.toList());
    }

    @Override
    public UUID getAggregateId() {
        return orderId;
    }

    @Value
    public class OrderedProduct {
        UUID productId;
        long amount;
    }
}
