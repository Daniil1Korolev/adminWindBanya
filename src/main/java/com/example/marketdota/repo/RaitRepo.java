package com.example.marketdota.repo;

import com.example.marketdota.model.RaitModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaitRepo extends JpaRepository<RaitModel, Long> {

    Iterable<RaitModel> findByNameContainingIgnoreCase(String code);

}
