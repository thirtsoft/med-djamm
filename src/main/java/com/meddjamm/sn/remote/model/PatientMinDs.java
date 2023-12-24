package com.meddjamm.sn.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientMinDs implements Serializable {

    private String index;
    private Date dateInscription;
    private String prenom;
    private String nom;
    private String sexe;
    private int age;
}