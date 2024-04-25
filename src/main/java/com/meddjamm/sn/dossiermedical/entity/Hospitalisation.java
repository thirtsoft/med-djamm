package com.meddjamm.sn.dossiermedical.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "hospitalisation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hospitalisation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_hospisatisation", nullable = true, unique = true)
    private int numeroHospitalisation;

    @Column(name = "patient_uid")
    private String code;

    @Column(name = "medecin_uid")
    private String matricule;

    private String resume;

    /*
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ObservationClinique> observationCliniqueList;*/

    @OneToOne(cascade = {CascadeType.ALL})
    private ObservationClinique observationClinique;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<ExamenComplementaire> examenComplementaires;

    @OneToOne(cascade = {CascadeType.ALL})
    private ExamenComplementaire examenComplementaire;

    @OneToOne(cascade = {CascadeType.ALL})
    private TraitementMedical traitementMedical;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<TraitementMedical> traitementMedicals;

    @OneToOne(cascade = {CascadeType.ALL})
//    @OneToOne(fetch = FetchType.EAGER, cascade = {
//            MERGE
//    })
//    @ToString.Exclude
    private Discussion discussion;

//    @OneToMany(cascade = CascadeType.ALL )
//    private List<Discussion> discussions;

    @OneToOne(cascade = {CascadeType.ALL})
    private Synthese synthese;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Synthese> syntheseList;


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
