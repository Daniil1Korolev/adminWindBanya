package com.example.banyaadmin.repo;

import com.example.banyaadmin.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepo extends JpaRepository<Log, Integer> {
}
