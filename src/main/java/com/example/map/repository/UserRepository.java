//16.
package com.example.map.repository;

import com.example.map.entity.UserEntity;
import com.example.oauthjwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
}
