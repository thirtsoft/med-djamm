package com.meddjamm.sn.dossiermedical.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "circuit_patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CircuitPatient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_circuit", nullable = true, unique = true)
    private int numeroCircuit;

    @Column(name = "patient_uid")
    private String code;

    @Column(name = "medecin_uid")
    private String matricule;

    private String type;

    private int etat;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObservationClinique> observationCliniqueList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamenComplementaire> examenComplementaires;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TraitementMedical> traitementMedicals;

    @CreatedBy
    private Long createdBy;
    @CreatedDate
    private Date createDate;

    private int estTransfere;

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
