package com.meddjamm.sn.config.motdepasse;

import lombok.Data;

@Data
public class ChangerMotDePasseRequest {
    private String email;
    private String ancienMotDePasse;
    private String nouveauMotDePasse;
}