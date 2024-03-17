package com.meddjamm.sn.dossiermedical.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "traitement_medical_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraitementMedicalItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "medicament_uid")
    private String code;

    private String psologie;

    private String nbrePrise;

    private String administrePar;

    private int est_administre;
}
