package com.helloworld.backend_api.pretest.repository;

import com.helloworld.backend_api.pretest.domain.UserPreTestSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreTestSessionRepository extends JpaRepository<UserPreTestSession, Long> {

}
