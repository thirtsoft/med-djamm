package com.meddjamm.sn.dossiermedical.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "observation_clinique")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObservationClinique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "motifs_hospitalisation_par_observation_clinique", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "motifs_hospitalisation")
    private Set<String> motifsHospitalisation;

    @Column(name = "histoire_maladie", columnDefinition = "TEXT")
    private String histoireMaladie;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Antecedent antecedent;

    /*
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private ExamenPhysique examenPhysique;*/

    /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "observationClinique", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamenPhysique> examenPhysiqueList;
*/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "observationClinique", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamenPhysique> examenPhysiqueList;

/*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "observationClinique", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExamenPhysique> examenPhysiqueList;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circuit_patient_uid")
    private CircuitPatient circuitPatient;

    @JoinColumn(name = "circuit_patient_id")
    private Long circuitPatientId;

    private Long createdBy;

    private Date createdDate;

    private int actif;

    public boolean isActif() {
        if (actif == 1) return true;
        else return false;
    }

    public void setActif(boolean actif) {
        if (actif == true) this.actif = 1;
        else this.actif = 0;
    }

}
