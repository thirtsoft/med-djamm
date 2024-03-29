package com.meddjamm.sn.exception;

public class ActionAlreadyExistException extends RuntimeException {
    public ActionAlreadyExistException(String message) {
        super(message);
    }
}