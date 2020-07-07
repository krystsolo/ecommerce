package com.example.ecommerce.monolith.inventory;

import com.example.ecommerce.monolith.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class Inventory {

    private final ItemRepository repository;

    public void addToStock(UUID productId, long amount) {
        Item item = repository.findByProductId(productId);

        if (item == null) {
            item = new Item(productId);
        }

        item.increaseStockBy(amount);
        repository.save(item);
    }

    public void updateStockFor(Order order) {
        order.getOrderItems().forEach(orderItem -> {
            Item item = repository.findByProductId(orderItem.getProductId());
            item.reduceStockBy(orderItem.getAmount());
        });
    }
}
