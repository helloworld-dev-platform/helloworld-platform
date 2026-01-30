package com.helloworld.backend_api.user.repository;

import com.helloworld.backend_api.leveltest.domain.UserLevelTestResult;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLevelTestResultRepository extends JpaRepository<UserLevelTestResult, Long> {

  Optional<UserLevelTestResult> findFirstByUserIdOrderByCompletedAtDesc(Long user);

}
