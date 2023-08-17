package com.example.blogspring.exception.patient;


import com.example.blogspring.exception.BusinessException;
import com.example.blogspring.exception.ErrorCode;

public class DuplicatedPatientLoginIdException extends BusinessException {

    public DuplicatedPatientLoginIdException() {
        super(ErrorCode.PATIENT_LOGIN_ID_ALREADY_EXISTS); // RuntimeException 클래스의 생성자를 호출합니다.
    }
}
