package com.example.SpringCoverAll.service;

import com.example.SpringCoverAll.model.Role;
import com.example.SpringCoverAll.model.Student;
import com.example.SpringCoverAll.repository.AuthRepository;
import com.example.SpringCoverAll.utils.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static com.example.SpringCoverAll.roles.ApplicationUserRole.STUDENT;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommonService {

    private final AuthRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Student getStudentById(int id) {
        Optional<Student> optional = repository.findById(id);
        if (optional.isEmpty()) throw new RuntimeException("User hasn't exist!");
        return optional.get();
    }

    @Transactional
    public Student registerNewStudent(Student student) {
        student.setPassword(passwordEncoder.getPasswordEncoder().encode(student.getPassword()));
        student.setRoles(Collections.singleton(new Role(null, STUDENT.name())));
        return repository.save(student);
    }

    @Transactional
    public Student updateStudent(Student student) {
        return repository.save(student);
    }

    @Transactional
    public Student deleteStudent(int id) {
        Student student = getStudentById(id);
        repository.deleteById(id);
        return student;
    }

    public boolean isStudentExist(Student student) {
        return repository.existsByUsername(student.getUsername());
    }
}
