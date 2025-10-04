package com.helloworld.backend_api.user.repository;

import com.helloworld.backend_api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
    public User findByUserEmail(String email);
}
