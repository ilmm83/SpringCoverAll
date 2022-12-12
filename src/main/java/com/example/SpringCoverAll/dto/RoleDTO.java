package com.example.SpringCoverAll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoleDTO {

    @NotEmpty
    @NotBlank
    private String name;
}
