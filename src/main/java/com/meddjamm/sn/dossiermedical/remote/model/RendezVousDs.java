package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVousDs implements Serializable {

    private String libelle;

    private String code;

    private Long createdBy;

    private String matricule;

    private String heure;

    private Date dateRendezVous;

    private Date createDate;

    private String nomCompletAgent;

    private int actif;

    private int etat;

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
