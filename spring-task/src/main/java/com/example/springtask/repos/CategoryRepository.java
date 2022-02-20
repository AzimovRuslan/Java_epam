package com.example.springtask.repos;

import com.example.springtask.domain.store.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
