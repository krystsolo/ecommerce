package com.example.ecommerce.eventdriven.order;

import org.springframework.data.repository.Repository;

import java.util.UUID;

interface OrderRepository extends Repository<Order, UUID> {
    Order getById(UUID orderId);
    void save(Order order);
}
