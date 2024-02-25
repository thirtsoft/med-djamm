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
public class HistoireMaladieDs implements Serializable {

    private Long id;

    private String indexPatient;

    private List<AtteinteDs> atteintePrecedents;

    private List<AtteinteDs> atteinteActuels;

    private int age;

    private String circonstance;

    private Date dateDiagnostic;

    private String infoComplementaire;

    private Date createdDate;

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