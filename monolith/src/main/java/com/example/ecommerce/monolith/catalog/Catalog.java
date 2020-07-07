package com.example.ecommerce.monolith.catalog;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(path = "catalog")
interface Catalog extends Repository<Product, UUID> {

    @Query("select p from products p where status = 'Available'")
    List<Product> findAll();

    @Modifying
    @Query("update products p set status = 'Archived' where p.id = :id")
    void delete(UUID id);
}
