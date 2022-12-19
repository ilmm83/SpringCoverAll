package com.example.SpringCoverAll.controller;

import com.example.SpringCoverAll.authentication.StudentDetailsService;
import com.example.SpringCoverAll.dto.StudentDTO;
import com.example.SpringCoverAll.model.Student;
import com.example.SpringCoverAll.security.jwt.JwtUtil;
import com.example.SpringCoverAll.service.CommonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class AuthController {

    private final CommonService service;
    private final ModelMapper mapper;
    private final JwtUtil jwtUtil;
    private final StudentDetailsService detailsService;

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBysId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(convertToStudentDTO(service.getStudentById(id)));
    }

    @GetMapping("/showCurrentUserInfo")
    public ResponseEntity<Authentication> showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }

    @PostMapping("/sign_up")
    public ResponseEntity<String> registerNewStudent(@RequestBody @Valid StudentDTO studentDTO, BindingResult bindingResult) {
        DTOValidation(bindingResult);

        Student student = convertToStudent(studentDTO);
        if (service.isStudentExist(student))
            throw new RuntimeException("User is already exist"); // todo: exc handl

        service.registerNewStudent(student);

        UserDetails userDetails = detailsService.loadUserByUsername(studentDTO.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok("Bearer " + token);
    }

    @PostMapping("/login")
    public ResponseEntity<StudentDTO> login(@RequestBody @Valid StudentDTO studentDTO, BindingResult bindingResult) {
        DTOValidation(bindingResult);
        if (!service.isStudentExist(convertToStudent(studentDTO)))
            throw new UsernameNotFoundException("User has not exist!");
        return ResponseEntity.ok(studentDTO);
    }

    private static void DTOValidation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (var error : bindingResult.getFieldErrors())
                sb.append(error.getField()).append(" -> ").append(error.getDefaultMessage()).append(";");
            throw new RuntimeException("Credentials are invalid " + sb); // todo: exc handle
        }
    }

    private StudentDTO convertToStudentDTO(Student student) {
        return mapper.map(student, StudentDTO.class);
    }

    private Student convertToStudent(StudentDTO studentDTO) {
        return mapper.map(studentDTO, Student.class);
    }
}
