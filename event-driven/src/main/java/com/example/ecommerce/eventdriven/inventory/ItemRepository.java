package com.example.ecommerce.eventdriven.inventory;

import org.springframework.data.repository.Repository;

import java.util.UUID;

interface ItemRepository extends Repository<Item, UUID> {

    Item findByProductId(UUID productId);
    void save(Item item);
}
