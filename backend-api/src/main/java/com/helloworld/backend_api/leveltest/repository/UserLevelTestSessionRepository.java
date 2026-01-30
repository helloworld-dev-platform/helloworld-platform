package com.helloworld.backend_api.leveltest.repository;

import com.helloworld.backend_api.leveltest.domain.UserLevelTestSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLevelTestSessionRepository extends JpaRepository<UserLevelTestSession, Long> {

}
