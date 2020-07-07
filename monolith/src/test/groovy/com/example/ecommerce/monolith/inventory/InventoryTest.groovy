package com.example.ecommerce.monolith.inventory

import com.example.ecommerce.monolith.order.Order
import spock.lang.Specification

class InventoryTest extends Specification {

    ItemRepository itemRepository = new InMemoryItemRepository()
    Inventory inventory = new Inventory(itemRepository)

    def "should add stock to existing item"() {
        given:
            UUID PRODUCT_ID = UUID.randomUUID()
            long AMOUNT = 5
            thereIsItemWith(PRODUCT_ID)
        when:
            inventory.addToStock(PRODUCT_ID, AMOUNT)
        then:
            itemRepository.findByProductId(PRODUCT_ID).amount == AMOUNT
    }

    def "should create and add stock to item"() {
        given:
            UUID PRODUCT_ID = UUID.randomUUID()
            long AMOUNT = 5
        when:
            inventory.addToStock(PRODUCT_ID, AMOUNT)
        then:
            itemRepository.findByProductId(PRODUCT_ID).amount == AMOUNT
    }

    def "should reduce stock of items from order"() {
        given:
            UUID PRODUCT_ID = UUID.randomUUID()
            long AMOUNT = 5
            inventory.addToStock(PRODUCT_ID, AMOUNT)
            Order order = new Order()
            order.addToOrder(PRODUCT_ID, AMOUNT)

        when:
            inventory.updateStockFor(order)

        then:
            itemRepository.findByProductId(PRODUCT_ID).amount == 0
    }

    def thereIsItemWith(UUID productId) {
        itemRepository.save(new Item(productId))
    }
}
