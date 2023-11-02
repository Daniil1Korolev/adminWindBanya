package com.example.marketdota.repo;

import com.example.marketdota.model.LessonsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonsRepo extends JpaRepository<LessonsModel, Long> {
    Iterable<LessonsModel> findByNameContainingIgnoreCase(String name);
    LessonsModel findByName(String name);
}
