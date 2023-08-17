package com.example.blogspring.service;


import com.example.blogspring.config.jwt.TokenProvider;
import com.example.blogspring.dto.TokenDto;
import com.example.blogspring.dto.patient.PatientRequestDto;
import com.example.blogspring.dto.patient.PatientResponseDto;
import com.example.blogspring.entity.patient.Patient;
import com.example.blogspring.exception.patient.DuplicatedPatientLoginIdException;
import com.example.blogspring.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final PatientRepository patientRepository;

    public PatientResponseDto signup(PatientRequestDto requestDto) {
        if (patientRepository.existsByloginId(requestDto.getLoginId())) {
            throw new DuplicatedPatientLoginIdException();
        }
        Patient patient = requestDto.toPatient(passwordEncoder);
        Patient savePatient = patientRepository.save(patient);
        return PatientResponseDto.builder()
                .loginId(savePatient.getLoginId())
                .build();
    }

    public TokenDto login(PatientRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);
    }

}
