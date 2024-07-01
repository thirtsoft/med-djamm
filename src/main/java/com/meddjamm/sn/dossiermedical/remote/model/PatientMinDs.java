package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientMinDs implements Serializable {

    private Long id;
    private String code;
    private Date dateAdmission;
    private String prenom;
    private String nom;
    private int isCircuitGenerated;
    private Long createdBy;
    private boolean est_accompagne;
    private String telephone;
    private Date dateNaissance;
    private LocalDateTime createdDate;
    private DiagnosticDs diagnosticDs;
    private int nombre_passage;
}