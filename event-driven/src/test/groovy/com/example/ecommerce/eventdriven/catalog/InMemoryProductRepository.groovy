package com.example.ecommerce.eventdriven.catalog

class InMemoryProductRepository implements Catalog{

    Map<UUID, Product> products = new HashMap<>()

    @Override
    List<Product> findAll() {
        return products.values().stream().findAll { p -> p.getStatus() == Product.Status.Available}
    }

    @Override
    void delete(UUID id) {
        products.remove(id)
    }

    @Override
    void save(Product product) {
        products.put(product.id, product)
    }
}
