package com.example.SpringCoverAll.service;

import com.example.SpringCoverAll.model.Student;
import com.example.SpringCoverAll.repository.AuthRepository;
import com.example.SpringCoverAll.utils.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommonServiceTest {

    @Mock
    private AuthRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CommonService service;

    private Student student;


    @BeforeEach
    void setUp() {
        passwordEncoder = new PasswordEncoder();
        service = new CommonService(repository, passwordEncoder);

        student = new Student();
        student.setId(1);
        student.setUsername("user1");
        student.setPassword("pass1");
    }

    @Test
    void canGetStudentById() {
        // given
        given(repository.findById(1)).willReturn(Optional.of(student));

        // when
        Student foundStudent = service.getStudentById(student.getId());

        // then
        assertThat(foundStudent).isNotNull();
    }

    @Test
    void canGetStudentByIdAndThrowException() {
        // given
        given(repository.findById(student.getId())).willReturn(Optional.empty());

        // when
        assertThrows(RuntimeException.class, () -> service.getStudentById(student.getId()));

        // then
        verify(repository, times(1)).findById(anyInt());
        assertThat(repository.findById(student.getId())).isEmpty();
    }

    @Test
    void canRegisterNewStudent() {
        // given - precondition or setup
        given(repository.save(student)).willReturn(student);

        System.out.println(repository);
        System.out.println(service);

        // when - action or the behavior that we are going to test
        Student savedStudent = service.registerNewStudent(student);
        System.out.println(savedStudent);

        // then - verify the output
        assertThat(savedStudent).isNotNull();
    }

    @Test
    void canDeleteStudent() {
        // given
        int student_id = 1;
        willDoNothing().given(repository).deleteById(student_id);

        // when
        service.deleteStudent(student_id);

        // then
        verify(repository, times(1)).deleteById(student_id);
    }

    @Test
    void isStudentExist() {
        // given
        given(repository.existsByUsername(student.getUsername())).willReturn(true);

        // when
        service.isStudentExist(student);

        // then
        verify(repository, times(1)).existsByUsername(student.getUsername());
    }
}