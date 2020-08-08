package com.example.ecommerce.eventdriven.inventory;

import com.example.ecommerce.eventdriven.commons.events.DomainEvent;
import com.example.ecommerce.eventdriven.commons.events.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@RequiredArgsConstructor
@Service
public class Inventory {

    private final ItemRepository repository;
    private final EventPublisher eventPublisher;

    public void addToStock(UUID productId, long amount) {
        Item item = repository.findByProductId(productId);
        item.increaseStockBy(amount);
        repository.save(item);
    }

    public void createItem(UUID productId) {
        repository.save(new Item(productId));
    }

    public void updateStockFor(UpdateStockCommand updateStockCommand) {
        List<DomainEvent> events = new ArrayList<>();
        updateStockCommand.getUpdatedItem().forEach(updatedItem -> {
            Item item = repository.findByProductId(updatedItem.getProductId());
            events.addAll(item.reduceStockBy(updatedItem.getAmount()));
        });
        eventPublisher.publish(events);
    }

    public long getStockFor(UUID productId) {
        return repository.findByProductId(productId).getAmount();
    }
}
