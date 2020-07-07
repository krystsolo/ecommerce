package com.example.ecommerce.monolith.order

import com.example.ecommerce.monolith.inventory.Inventory
import spock.lang.Specification

class OrderServiceTest extends Specification {

    Inventory inventory = Mock()
    OrderRepository orderRepository = new InMemoryOrderRepository()
    OrderService orderService = new OrderService(orderRepository, inventory)

    def "new order created"() {
        when:
            Order order = orderService.createOrder()
        then:
            orderRepository.getById(order.id) != null
    }

    def "complete order"() {
        given:
            Order order = orderService.createOrder()
            order.addToOrder(UUID.randomUUID(), 5)
        when:
            orderService.completeOrder(order.id)
        then:
            orderRepository.getById(order.id).status == Order.Status.Completed
            1 * inventory.updateStockFor(order)
    }

    def "cannot complete order when is empty"() {
        given:
            Order order = orderService.createOrder()
        when:
            orderService.completeOrder(order.id)
        then:
            thrown(IllegalStateException)
    }

    def "add product to order"() {
        given:
            Order order = orderService.createOrder()
        when:
            orderService.addToOrder(order.id, UUID.randomUUID(), 5)
        then:
            orderRepository.getById(order.id).orderItems.size() == 1
    }
}
