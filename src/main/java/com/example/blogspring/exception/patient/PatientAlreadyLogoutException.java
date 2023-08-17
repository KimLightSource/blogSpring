package com.example.blogspring.exception.patient;


import com.example.blogspring.exception.BusinessException;
import com.example.blogspring.exception.ErrorCode;

public class PatientAlreadyLogoutException extends BusinessException {

    public PatientAlreadyLogoutException() {
        super(ErrorCode.PATIENT_ALREADY_LOGOUT);
    }
}
