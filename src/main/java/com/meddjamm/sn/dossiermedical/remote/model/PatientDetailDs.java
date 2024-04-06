package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDetailDs implements Serializable {
    private Long id;
    private String code;
    private String prenom;
    private String nom;
    private String sexe;
    private String civilite;
    private String address;
    private Date dateNaissance;
    private int age;
    private String numeroTelephone;
    private String profession;
    private String situationMatrimonial;
    private String photo;
    private String race;
    private String ethnie;
    private String origine;
    private String nationalite;
    private String originePere;
    private String origineMere;
    private String prototype;
    private String consanguinite;
    private String niveauSocialEconomique;
    private String regimeAlimentaire;
    private Date dateAdmission;
    private PersonneConfianceDs personneConfianceDs;
    private int isCircuitGenerated;
    private Long createdBy;
    private boolean est_accompagne;
    private DiagnosticDs diagnosticDs;
}
