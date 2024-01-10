package com.meddjamm.sn.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticPrincipalDetailDs implements Serializable {

    private Long id;

    private Long scoreObtenu;

    private String indexPatient;

    private PatientDetailDs patientDetailDs;

    private MaladieDs maladieDs;

    private ClassificationDs classificationDs;

    private List<CritereUtiliseDs> critereUtiliseDsList;

    private Date createDate;

    private int actif;

    public boolean isActif() {
        if (actif == 1)
            return true;
        else
            return false;
    }

    public void setActif(boolean actif) {
        if (actif == true)
            this.actif = 1;
        else
            this.actif = 0;
    }
}
