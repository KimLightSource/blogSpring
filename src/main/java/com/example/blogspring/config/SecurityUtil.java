package com.example.blogspring.config;


import com.example.blogspring.exception.patient.PatientAlreadyLogoutException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtil {

    private SecurityUtil() {}

    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new PatientAlreadyLogoutException();
        }

        return Long.parseLong(authentication.getName());
    }



}
