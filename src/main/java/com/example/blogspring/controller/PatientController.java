package com.example.blogspring.controller;

import com.example.blogspring.dto.patient.PatientRequestDto;
import com.example.blogspring.dto.patient.PatientResponseDto;
import com.example.blogspring.global.ResultCode;
import com.example.blogspring.global.ResultResponse;
import com.example.blogspring.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/login")
    public ResponseEntity<ResultResponse> login(@RequestBody PatientRequestDto requestDto) {
        ResultResponse result = ResultResponse.of(ResultCode.LOGIN_SUCCESS, patientService.login(requestDto));
        return ResponseEntity.ok().body(result);

    }

    @PostMapping("/signup")
    public ResponseEntity<ResultResponse> signup(@RequestBody PatientRequestDto requestDto) {
        PatientResponseDto patientResponseDto = patientService.signup(requestDto);
        ResultResponse result = ResultResponse.of(ResultCode.REGISTER_SUCCESS, patientResponseDto);
        return ResponseEntity.ok().body(result);
    }

    // WebSecurityConfig 에서 logout() 처리후 해당 메서드가 호출됨
    @GetMapping("/logout")
    public ResponseEntity<ResultResponse> logout() {
        ResultResponse result = ResultResponse.of(ResultCode.LOGOUT_SUCCESS, null);
        return ResponseEntity.ok().body(result);
    }

}
