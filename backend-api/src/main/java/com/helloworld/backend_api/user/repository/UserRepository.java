package com.helloworld.backend_api.user.repository;

import com.helloworld.backend_api.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    public Users findByUsername(String username);
}
