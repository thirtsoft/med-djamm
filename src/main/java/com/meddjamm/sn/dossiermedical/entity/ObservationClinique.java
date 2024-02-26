package com.meddjamm.sn.dossiermedical.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
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

    @Column(name = "histoire_maladie")
    private String histoireMaladie;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Antecedent antecedent;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private ExamenPhysique examenPhysique;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circuit_patient_uid")
    private CircuitPatient circuitPatient;

    @JoinColumn(name = "circuit_patient_id")
    private Long circuitPatientId;

    private Long createdBy;

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
