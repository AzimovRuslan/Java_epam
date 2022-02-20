package com.example.springtask.repos;

import com.example.springtask.domain.store.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
