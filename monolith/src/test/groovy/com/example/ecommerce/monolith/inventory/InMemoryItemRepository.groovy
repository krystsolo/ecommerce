package com.example.ecommerce.monolith.inventory

class InMemoryItemRepository implements ItemRepository{

    Map<UUID, Item> items = new HashMap<>()

    @Override
    Item findByProductId(UUID productId) {
        return items.get(productId)
    }

    @Override
    void save(Item item) {
        items.put(item.productId, item)
    }
}
