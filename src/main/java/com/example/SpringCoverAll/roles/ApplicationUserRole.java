package com.example.SpringCoverAll.roles;


import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.SpringCoverAll.roles.ApplicationUserPermission.*;

@Getter
public enum ApplicationUserRole {

    STUDENT(Sets.newHashSet(COURSE_READ)),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));



    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permissions = permission;
    }

    public Set<SimpleGrantedAuthority> getAuthority(){
        Set<SimpleGrantedAuthority> authority = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        authority.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authority;
    }
}
