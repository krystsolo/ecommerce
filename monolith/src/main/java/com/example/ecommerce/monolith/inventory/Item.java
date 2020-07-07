package com.example.ecommerce.monolith.inventory;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Setter(AccessLevel.NONE)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Item {

    private @Id UUID id;
    private UUID productId;
    private long amount;

    public Item(UUID productId) {
        this.id = UUID.randomUUID();
        this.productId = productId;
        this.amount = 0;
    }

    void reduceStockBy(long amount) {
        if (this.amount < amount) {
            throw new IllegalArgumentException("Stock insufficient");
        }

        this.amount =- amount;
    }

    void increaseStockBy(long amount) {
        this.amount =+ amount;
    }
}
