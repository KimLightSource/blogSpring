package com.example.blogspring.service;

import com.example.blogspring.entity.patient.Patient;
import com.example.blogspring.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor //토큰 인증시 해당 서비스에서 DB에 쿼리를 날린다.
public class CustomUserDetailService implements UserDetailsService {
    private final PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        return patientRepository.findByLoginId(loginId)
                               .map(this::createUserDetails)
                               .orElseThrow(() -> new UsernameNotFoundException(loginId + " 이 존재하지 않습니다."));
    }

    private UserDetails createUserDetails(Patient patient) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(patient.getUserRole().toString());

        return new User(
                String.valueOf(patient.getPatientSeq()),
                patient.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
