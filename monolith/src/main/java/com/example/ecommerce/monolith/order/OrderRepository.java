package com.example.ecommerce.monolith.order;

import org.springframework.data.repository.Repository;

import java.util.UUID;

interface OrderRepository extends Repository<Order, UUID> {
    Order getById(UUID orderId);
    void save(Order order);
}
