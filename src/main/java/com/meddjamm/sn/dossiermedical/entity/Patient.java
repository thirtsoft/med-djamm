package com.meddjamm.sn.dossiermedical.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String prenom;
    private String nom;
    private String sexe;
    private String civilite;
    private String address;
    private Date dateNaissance;
    private int age;
    private String numeroTelephone;
    private String profession;
    private String situationMatrimonial;
    private String photo;
    private String race;
    private String ethnie;
    private String origine;
    private String nationalite;
    private String originePere;
    private String origineMere;
    private String prototype;
    private String consanguinite;
    private String niveauSocialEconomique;
    private String regimeAlimentaire;
    private Date dateAdmission;

    @Column(name = "is_circuit_generated", columnDefinition = "int default 0")
    private int isCircuitGenerated;

    @Column(name = "est_accompagne", columnDefinition = "int default 0")
    private int isNotSingle;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private PersonneConfiance personneConfiance;

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
