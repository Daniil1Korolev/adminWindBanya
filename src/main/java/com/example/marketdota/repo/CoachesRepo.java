package com.example.marketdota.repo;

import com.example.marketdota.model.CoachesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachesRepo extends JpaRepository<CoachesModel, Long> {
    Iterable<CoachesModel> findByNameContainingIgnoreCase(String name);
}