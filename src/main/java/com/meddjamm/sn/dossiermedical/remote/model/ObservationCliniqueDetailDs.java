package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObservationCliniqueDetailDs {

    private Long id;

    private List<String> motifsHospitalisation;

    private String histoireMaladie;

    private AntecedentDs antecedentDs;

    // private ExamenPhysiqueDs examenPhysiqueDs;

    private List<ExamenPhysiqueDs> examenPhysiqueDs;

    private Long circuitPatientId;

    private Long createdBy;

    private Date createdDate;

    private int actif;

    public boolean isActif() {
        if (actif == 1) return true;
        else return false;
    }

    public void setActif(boolean actif) {
        if (actif == true) this.actif = 1;
        else this.actif = 0;
    }
}
