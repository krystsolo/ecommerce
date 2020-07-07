package com.example.ecommerce.monolith.inventory

class InMemoryItemRepository implements ItemRepository{

    Map<UUID, Item> books = new HashMap<>()

    @Override
    Item findByProductId(UUID productId) {
        return books.get(productId)
    }

    @Override
    void save(Item item) {
        books.put(item.productId, item)
    }
}
