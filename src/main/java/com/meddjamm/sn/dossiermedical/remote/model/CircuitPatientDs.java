package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CircuitPatientDs {

    private Long id;

    private String numeroCircuit;

    private String code;

    private String matricule;

    private String type;

    private int etat;

    private Long createdBy;

    private String nomCompletAgent;

    /*
    private ObservationCliniqueDs observationCliniqueDs;

    private ExamenComplementaireDs examenComplementaireDs;

    private TraitementMedicalDs traitementMedicalDs;

    private List<ObservationCliniqueDs> observationCliniqueDs;

    private List<ExamenComplementaireDs> examenComplementaireDs;

    private List<TraitementMedicalDs> traitementMedicalDs;
    */

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