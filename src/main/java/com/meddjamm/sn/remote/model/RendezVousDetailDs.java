package com.meddjamm.sn.remote.model;

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
    private int numeroRendezVous;
    private Date dateRendezVous;
    private String heure;
    private Date createDate;
    private String libelleEtat;
    private int actif;
    private int etat;
    private String index;
    private PatientDetailDs patient;

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
