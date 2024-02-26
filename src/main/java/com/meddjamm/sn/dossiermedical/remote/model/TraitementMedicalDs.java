package com.meddjamm.sn.dossiermedical.remote.model;

import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.OrdonnanceItemDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitementMedicalDs {

    private Long id;

    private Long circuitPatientId;

    private CircuitPatientDs circuitPatientDs;

    private String protocole;

    private String protocoleFileName;

    private List<OrdonnanceItemDs> ordonnanceItemDs;

    private Date createdDate;

    private Long createdBy;

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