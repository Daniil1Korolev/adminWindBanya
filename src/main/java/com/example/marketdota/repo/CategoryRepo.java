package com.example.marketdota.repo;

import com.example.marketdota.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryModel, Long> {

    Iterable<CategoryModel> findByNameContainingIgnoreCase(String name);

}
