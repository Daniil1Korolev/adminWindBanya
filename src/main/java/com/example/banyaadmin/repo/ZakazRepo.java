package com.example.banyaadmin.repo;

import com.example.banyaadmin.model.ZakazModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZakazRepo extends JpaRepository<ZakazModel, Long> {
    Iterable<ZakazModel> findByNameContainingIgnoreCase(String name);
}
