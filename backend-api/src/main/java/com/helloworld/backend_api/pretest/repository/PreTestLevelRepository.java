package com.helloworld.backend_api.pretest.repository;

import com.helloworld.backend_api.pretest.domain.PreTestLevel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreTestLevelRepository extends JpaRepository<PreTestLevel, Long> {

  Optional<PreTestLevel> findByPreTestLevelName(String name);
}
