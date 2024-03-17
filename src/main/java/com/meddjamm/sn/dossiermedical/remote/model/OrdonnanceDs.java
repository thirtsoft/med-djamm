package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdonnanceDs {

    private Long id;

    private Long circuitPatientId;

    private CircuitPatientDs circuitPatientDs;

    private List<OrdonnanceItemDs> ordonnanceItemDs;

    private Date createdDate;

    private Long createdBy;

    private String nomCompletAgent;

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
