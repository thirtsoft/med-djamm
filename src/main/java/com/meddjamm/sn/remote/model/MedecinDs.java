package com.meddjamm.sn.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedecinDs {

    private Long id;

    private String matricule;

    private String civilite;

    private String nom;

    private String prenom;

    private String sexe;

    private String telephone;

    private String specialite;

    private String email;

    private Date dateRecrutement;
}
