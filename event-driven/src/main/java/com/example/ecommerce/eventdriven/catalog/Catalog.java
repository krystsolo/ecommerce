package com.example.ecommerce.eventdriven.catalog;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Repository
interface Catalog extends Repository<Product, UUID> {

    @Query("select p from products p where status = 'Available'")
    List<Product> findAll();

    @Modifying
    @Query("update products p set status = 'Archived' where p.id = :id")
    void delete(UUID id);

    void save(Product product);
}
