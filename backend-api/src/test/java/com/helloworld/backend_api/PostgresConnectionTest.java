package com.helloworld.backend_api;

import com.helloworld.backend_api.domain.TestUser;
import com.helloworld.backend_api.repository.TestUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PostgresConnectionTest {
    @Autowired
    TestUserRepository testRepositoty;

    @Test
    void testSaveAndFind(){
        TestUser user = new TestUser();
        user.setName("postgres-connection-ok");
        testRepositoty.save(user);

        Optional<TestUser> found = testRepositoty.findById(user.getId());
        assertTrue(found.isPresent());
        assertEquals("postgres-connection-ok", found.get().getName());
    }
}
