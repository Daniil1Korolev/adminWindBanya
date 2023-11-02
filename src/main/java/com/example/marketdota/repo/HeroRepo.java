package com.example.marketdota.repo;

import com.example.marketdota.model.HeroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepo extends JpaRepository<HeroModel, Long> {
    Iterable<HeroModel> findByNameContainingIgnoreCase(String name);
}
