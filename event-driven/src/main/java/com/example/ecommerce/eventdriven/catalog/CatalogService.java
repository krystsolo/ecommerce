package com.example.ecommerce.eventdriven.catalog;

import com.example.ecommerce.eventdriven.commons.events.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final Catalog catalog;
    private final EventPublisher publisher;

    public List<Product> findAll() {
        return catalog.findAll();
    }

    public void remove(UUID productId) {
        catalog.delete(productId);
    }

    public void create(String name, BigDecimal price) {
        Product product = new Product(name, price);
        catalog.save(product);
        publisher.publish(new ProductCreated(product.getId()));
    }
}
