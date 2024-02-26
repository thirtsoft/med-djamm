package com.meddjamm.sn.dossiermedical.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "examen_physique")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamenPhysique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "examen_general")
    private String examenGeneral;

    @Column(name = "examen_appareil")
    private String examenAppareil;

    @Column(name = "pression_arterielle_systolique")
    private int pressionArterielS;

    @Column(name = "pression_arterielle_diastolique")
    private int pressionArterielD;

    private float temperature;

    @Column(name = "frequence_cardique")
    private int frequenceC;

    @Column(name = "frequence_respiratoire")
    private int frequenceR;

    @Column(name = "saturation_oxygene")
    private float saturationOxygene;

    private float diurese;

    private float poids;

    private float taille;

    private float imc;

    @Column(name = "tour_taille")
    private float tourTaille;

    @Column(name = "tour_hanche")
    private float tourHanche;

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