package com.meddjamm.sn.config.remote.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String prenom;
    private String email;
    private String password;
    private String profilCode;
    private String typeUtilisateur;
}