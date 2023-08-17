package com.example.blogspring.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    PATIENT("ROLE_PATIENT"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}
