package com.example.banyaadmin.repo;

import com.example.banyaadmin.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserModel, Long> {
    Iterable<UserModel> findByNameContainingIgnoreCase(String surname);

    UserModel findByEmail(String email);

}
