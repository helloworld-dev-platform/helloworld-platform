package com.helloworld.backend_api.leveltest.repository;

import com.helloworld.backend_api.leveltest.domain.LevelTestLevel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelTestLevelRepository extends JpaRepository<LevelTestLevel, Long> {

  Optional<LevelTestLevel> findByLevelTestLevelName(String name);
}
