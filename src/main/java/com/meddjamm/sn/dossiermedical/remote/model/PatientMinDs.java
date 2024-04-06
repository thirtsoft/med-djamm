package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientMinDs implements Serializable {
    private String code;
    private Date dateAdmission;
    private String prenom;
    private String nom;
    //    private String sexe;
//    private int age;
    private int isCircuitGenerated;
    private Long createdBy;
    private boolean est_accompagne;
    private String telephone;
    private Date dateNaissance;
}