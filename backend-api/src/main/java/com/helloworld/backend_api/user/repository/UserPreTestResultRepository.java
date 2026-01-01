package com.helloworld.backend_api.user.repository;

import com.helloworld.backend_api.pretest.domain.UserPreTestResult;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreTestResultRepository extends JpaRepository<UserPreTestResult, Long> {

  Optional<UserPreTestResult> findFirstByUserIdOrderByCompletedAtDesc(Long user);

}
