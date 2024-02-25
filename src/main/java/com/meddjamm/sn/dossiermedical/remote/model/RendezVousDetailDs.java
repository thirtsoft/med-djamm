package com.meddjamm.sn.dossiermedical.remote.model;

import com.meddjamm.sn.remote.model.MedecinDetailDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVousDetailDs implements Serializable {
    private Long id;
    private String libelle;
    private String code;
    private PatientDetailDs patient;
    private String matricule;
    private MedecinDetailDs medecinDetailDs;
    private Date dateRendezVous;
    private String heure;
    private Date createDate;
    private String libelleEtat;
    private Long createdBy;
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
