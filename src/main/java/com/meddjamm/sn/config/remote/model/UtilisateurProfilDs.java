package com.meddjamm.sn.config.remote.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurProfilDs {

    private Long id;
    private String codeUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String profileCode;
    private String matricule;
    private String civilite;
    private String sexe;
    private String telephone;
    private Date dateRecrutement;
    private String fonction;
    private String adresse;
    private String typeUtilisateur;
}
