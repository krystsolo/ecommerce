package com.example.ecommerce.eventdriven.order;

import lombok.*;

import javax.persistence.*;
import java.util.*;

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
    private List<OrderItem> orderItems;

    private Status status;

    Order() {
        this.id = UUID.randomUUID();
        this.orderItems = new ArrayList<>();
        this.status = Status.Submitted;
    }

    OrderCompleted complete() {
        if (orderItems.isEmpty()) {
            throw new IllegalStateException("Order list cannot be empty");
        }

        status = Status.Completed;
        return new OrderCompleted(id, orderItems);
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
