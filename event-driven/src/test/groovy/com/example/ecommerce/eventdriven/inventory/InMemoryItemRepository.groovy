package com.example.ecommerce.eventdriven.inventory

class InMemoryItemRepository implements ItemRepository {

    Map<UUID, Item> items = new HashMap<>()

    @Override
    Item findByProductId(UUID productId) {
        return items.find{ item -> item.value.productId == productId}.value as Item
    }

    @Override
    void save(Item item) {
        this.items.put(item.id, item)
    }
}
