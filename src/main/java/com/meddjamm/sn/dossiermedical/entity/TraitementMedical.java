package com.meddjamm.sn.dossiermedical.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "traitement_medical")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitementMedical implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String protocole;

    private String protocoleFileName;

    @Column(name = "patient_uid")
    private String codePatient;

    @Column(name = "medecin_uid")
    private String matricule;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ordonnance_item_par_traitement", joinColumns =
    @JoinColumn(name = "traitement_uid"),
            inverseJoinColumns = @JoinColumn(name = "ordonnance_item_uid"))
    private Set<OrdonnanceItem> ordonnanceItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circuit_patient_uid")
    private CircuitPatient circuitPatient;

    @JoinColumn(name = "circuit_patient_id")
    private Long circuitPatientId;

    private Date createdDate;

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
