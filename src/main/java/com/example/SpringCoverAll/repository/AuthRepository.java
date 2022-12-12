package com.example.SpringCoverAll.repository;

import com.example.SpringCoverAll.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Student, Integer> {

    Boolean existsByUsername(String username);

    Optional<Student> findByUsername(String username);
}
