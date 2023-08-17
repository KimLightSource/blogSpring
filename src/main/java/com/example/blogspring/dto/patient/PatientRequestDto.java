package com.example.blogspring.dto.patient;

import com.example.blogspring.entity.UserRole;
import com.example.blogspring.entity.patient.Patient;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

public record PatientRequestDto (
        String loginId,
        String password
){
    public Patient toPatient(PasswordEncoder passwordEncoder) {
        return Patient.builder()
                      .loginId(loginId)
                      .password(passwordEncoder.encode(password))
                      .userRole(UserRole.PATIENT)
                      .build();
    }

    //아이디와 비밀번호 일치 검증
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(loginId,password);
    }
}
