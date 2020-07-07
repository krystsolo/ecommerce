package com.example.ecommerce.monolith.order

class InMemoryOrderRepository implements OrderRepository {

    Map<UUID, Order> orders = new HashMap<>()

    @Override
    Order getById(UUID orderId) {
        return orders.get(orderId)
    }

    @Override
    void save(Order order) {
        orders.put(order.id, order)
    }
}
