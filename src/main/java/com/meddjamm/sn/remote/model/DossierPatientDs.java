package com.meddjamm.sn.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierPatientDs implements Serializable {

    private Long id;

    private String numeroDossier;

    private String indexPatient;

    private String indexMedecin;

    private DiagnosticPrincipalDs diagnosticPrincipalDs;

    private DiagnosticAssocieDs diagnosticAssocieDs;

    private HistoireMaladieDs histoireMaladieDs;

    private AntecedentMedicauxDs antecedentMedicauxDs;

    private AntecedentChirurgieDs antecedentChirurgieDs;

    private AntecedentGynecologieDs antecedentGynecologieDs;

    private AntecedentFamilialDs antecedentFamilialDs;

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
