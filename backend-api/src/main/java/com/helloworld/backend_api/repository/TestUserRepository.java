package com.helloworld.backend_api.repository;

import com.helloworld.backend_api.domain.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestUserRepository extends JpaRepository<TestUser, Long> {
}
