package com.example.SpringCoverAll.roles;

import lombok.Getter;

@Getter
public enum ApplicationUserPermission {

    COURSE_WRITE("course:write"),
    COURSE_READ("course:read"),
    STUDENT_WRITE("student:write"),
    STUDENT_READ("student:read");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
