package com.example.ecommerce.monolith.order;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@Setter(AccessLevel.NONE)
@EqualsAndHashCode(of = "id")
@Entity
public class Order {

    enum Status {
        Completed, Submitted
    }

    private @Id UUID id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems;

    private Status status;

    Order() {
        this.id = UUID.randomUUID();
        this.orderItems = new HashSet<>();
        this.status = Status.Submitted;
    }

    void complete() {
        if (orderItems.isEmpty()) {
            throw new IllegalStateException("Order list cannot be empty");
        }

        status = Status.Completed;
    }

    void addToOrder(UUID productId, long amount) {
        Optional<OrderItem> orderItem = orderItems.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (orderItem.isPresent()) {
            orderItem.ifPresent(item -> item.increaseQuantityBy(amount));
        } else {
            orderItems.add(new OrderItem(productId, amount));
        }
    }

    void removeFromOrder(UUID productId, long amount) {
        OrderItem orderItem = orderItems.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no such product in order"));

        orderItem.decreaseQuantityBy(amount);

        if (orderItem.getAmount() == 0) {
            orderItems.remove(orderItem);
        }
    }
}
