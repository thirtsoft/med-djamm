package com.meddjamm.sn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DossierMedicalException extends RuntimeException {
    public DossierMedicalException(String message) {
        super(message);
    }
}
