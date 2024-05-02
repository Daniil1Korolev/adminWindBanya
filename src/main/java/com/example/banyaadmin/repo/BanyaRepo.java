package com.example.banyaadmin.repo;

import com.example.banyaadmin.model.BanyaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanyaRepo extends JpaRepository<BanyaModel, Long> {
    Iterable<BanyaModel> findByNameContainingIgnoreCase(String name);
}