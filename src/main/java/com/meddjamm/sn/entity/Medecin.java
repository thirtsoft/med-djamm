package com.meddjamm.sn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "medecin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medecin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String matricule;

    private String civilite;

    private String nom;

    private String prenom;

    private String sexe;

    private String telephone;

    private String specialite;

    private String email;

    private Date dateRecrutement;
}
