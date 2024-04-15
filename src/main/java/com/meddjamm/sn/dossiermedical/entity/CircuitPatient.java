package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "circuit_patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CircuitPatient extends AbstractAuditableEntity implements Serializable {

    @Column(name = "numero_circuit", nullable = true, unique = true)
    private int numeroCircuit;

    @Column(name = "patient_uid")
    private String code;

    @Column(name = "medecin_uid")
    private String matricule;

    private String type;

    private int etat;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ObservationClinique> observationCliniqueList;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ExamenComplementaire> examenComplementaires;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<TraitementMedical> traitementMedicals;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Synthese> syntheseList;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Discussion> discussions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ordonnance> ordonnances;

    /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consultation> consultations;*/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvisSpecialiste> avisSpecialistes;

    /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamenBiologique> examenBiologiques;
*/
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuitPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultationMedical> consultationMedicals;


    //    @CreatedBy
    private Long createdBy;
    //    @CreatedDate
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
