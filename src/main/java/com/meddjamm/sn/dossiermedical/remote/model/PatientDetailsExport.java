package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDetailsExport implements Serializable {

    private String code;
    private String nom;
    private String prenom;
    private Date dateAdmission;
    private String address;
    private String numeroTelephone;
    private List<HospitalisationDsExport> hospitalisation;
}
