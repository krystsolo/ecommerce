package com.example.ecommerce.eventdriven.inventory;

import com.example.ecommerce.eventdriven.catalog.ProductCreated;
import com.example.ecommerce.eventdriven.order.OrderSubmitted;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class InventoryListener {

    private final Inventory inventory;

    @EventListener
    public void onProductCreatedInCatalog(ProductCreated event) {
        inventory.createItem(event.getProductId());
    }

    @EventListener
    public void updateStockFor(OrderSubmitted event) {
        List<UpdatedItem> updatedItems = event.getOrderedProducts().stream()
                .map(ordered -> new UpdatedItem(ordered.getProductId(), ordered.getAmount()))
                .collect(Collectors.toList());
        inventory.updateStockFor(new UpdateStockCommand(updatedItems));
    }
}
