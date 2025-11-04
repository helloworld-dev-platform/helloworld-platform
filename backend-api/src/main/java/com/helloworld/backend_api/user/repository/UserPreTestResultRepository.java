package com.helloworld.backend_api.user.repository;

import com.helloworld.backend_api.user.domain.UserPretestResult;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreTestResultRepository extends JpaRepository<UserPretestResult, Long> {

  Optional<UserPretestResult> findFirstByUserIdOrderByCompletedAtDesc(Long user);

}
