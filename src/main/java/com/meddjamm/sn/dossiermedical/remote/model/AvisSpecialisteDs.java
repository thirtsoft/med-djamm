package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvisSpecialisteDs {

    private Long id;

    private String resume;

    private Long circuitPatientId;

    private CircuitPatientDs circuitPatientDs;

    private Date createdDate;

    private Long createdBy;

    private String nomCompletAgent;


    private int actif;

    public boolean isActif() {
        return actif == 1;
    }

    public void setActif(boolean actif) {
        if (actif)
            this.actif = 1;
        else
            this.actif = 0;
    }
}
