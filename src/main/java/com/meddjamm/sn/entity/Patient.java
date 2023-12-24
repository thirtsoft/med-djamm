package com.meddjamm.sn.entity;

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
    private String index;
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
    private Date dateInscription;
    private int actif;

    public Patient(Long id, String prenom, String nom, String civilite, String address, String numeroTelephone, int actif) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.civilite = civilite;
        this.address = address;
        this.numeroTelephone = numeroTelephone;
        this.actif = actif;
    }

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
