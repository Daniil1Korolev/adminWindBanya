package com.example.marketdota.repo;

import com.example.marketdota.model.DotaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DotaRepo extends JpaRepository<DotaModel, Long> {
    Iterable<DotaModel> findByNameContainingIgnoreCase(String name);
}
