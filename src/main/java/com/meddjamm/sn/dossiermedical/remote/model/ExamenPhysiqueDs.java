package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamenPhysiqueDs {

    private Long id;

    private String examenGeneral;

    private String examenAppareil;

    private int pressionArterielS;

    private int pressionArterielD;

    private float temperature;

    private int frequenceC;

    private int frequenceR;

    private float saturationOxygene;

    private float diurese;

    private float poids;

    private float taille;

    private float imc;

    private float tourTaille;

    private float tourHanche;

    private int glycemie;

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