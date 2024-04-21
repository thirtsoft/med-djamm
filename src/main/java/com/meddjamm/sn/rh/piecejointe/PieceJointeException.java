package com.meddjamm.sn.rh.piecejointe;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PieceJointeException extends RuntimeException {

    public PieceJointeException(String s) {
        super(s);
    }
}
