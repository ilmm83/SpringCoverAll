package com.example.SpringCoverAll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentDTO {

    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 100, message = "Name should be between 3 and 100 characters!")
    private String username;

    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 30, message = "Password should be between 3 and 30 characters!")
    private String password;
}
