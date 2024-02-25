package com.meddjamm.sn.remote.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequest implements Serializable {

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
}