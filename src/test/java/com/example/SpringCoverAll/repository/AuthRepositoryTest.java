package com.example.SpringCoverAll.repository;

import com.example.SpringCoverAll.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class AuthRepositoryTest {

    @Autowired
    private AuthRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Is exist with that username")
    void existsByUsername() {
        Student student = new Student();
        student.setUsername("user1");
        student.setPassword("pass1");

        repository.save(student);

        String expect = repository.findByUsername(student.getUsername()).get().getUsername();
        assertThat(expect).isEqualTo("user1");
    }

    @Test
    void findByUsername() {
    }
}