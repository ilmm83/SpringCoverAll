package com.example.SpringCoverAll.authentication;

import com.example.SpringCoverAll.model.Student;
import com.example.SpringCoverAll.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentDetailsService implements UserDetailsService {

    private final AuthRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> optional = repository.findByUsername(username);
        if (optional.isEmpty()) throw new IllegalStateException("User hasn't exist!");
        return new StudentDetails(optional.get());
    }
}
