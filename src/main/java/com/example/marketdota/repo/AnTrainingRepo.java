package com.example.marketdota.repo;

import com.example.marketdota.model.AnTrainingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnTrainingRepo extends JpaRepository<AnTrainingModel, Long> {
    Iterable<AnTrainingModel> findAllByMonthContainsIgnoreCase(String month);
}
