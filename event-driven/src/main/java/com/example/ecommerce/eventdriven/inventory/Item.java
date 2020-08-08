package com.example.ecommerce.eventdriven.inventory;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Setter(AccessLevel.NONE)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
class Item {

    private @Id UUID id;
    private UUID productId;
    private long amount;

    Item(UUID productId) {
        this.id = UUID.randomUUID();
        this.productId = productId;
        this.amount = 0;
    }

    List<StockEvent> reduceStockBy(long amount) {
        List<StockEvent> events = new ArrayList<>();
        if (this.amount < amount) {
            events.add(new StockEvent.InsufficientStockIssued(productId, amount - this.amount));
        }

        events.add(new StockEvent.ItemTakenFromStock(productId, amount));
        this.amount = this.amount - amount;
        return events;
    }

    void increaseStockBy(long amount) {
        this.amount =+ amount;
    }
}
