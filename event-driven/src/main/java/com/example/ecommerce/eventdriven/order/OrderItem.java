package com.example.ecommerce.eventdriven.order;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Setter(AccessLevel.NONE)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
class OrderItem {

    private @Id UUID id;
    private UUID productId;
    private long amount;

    OrderItem(UUID productId, long amount) {
        this.id = UUID.randomUUID();
        this.productId = productId;
        this.amount = amount;
    }

    void increaseQuantityBy(long amount) {
        this.amount =+ amount;
    }

    void decreaseQuantityBy(long amount) {
        if (this.amount > amount) {
            this.amount =- amount;
        } else {
            this.amount = 0;
        }
    }
}
