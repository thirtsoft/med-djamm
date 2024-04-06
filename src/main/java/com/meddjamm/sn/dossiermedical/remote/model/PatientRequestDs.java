package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDs {

    @NotNull
    private String code;
    @NotNull
    private String prenom;
    @NotNull
    private String nom;
    @NotNull
    private String sexe;
    private String civilite;
    private String address;
    @NotNull
    private Date dateNaissance;
    @NotNull
    private int age;
    @NotNull
    private String telephone;
    private String profession;
    private String situationMatrimonial;
    private String nationalite;
    private PersonneConfianceDs personneConfianceDs;
    private int isCircuitGenerated;
    private boolean est_accompagne;

}
