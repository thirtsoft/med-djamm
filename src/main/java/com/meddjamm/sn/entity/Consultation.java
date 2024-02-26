package com.meddjamm.sn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "consultation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "patient_uid")
    private String indexPatient;

    @Column(name = "medecin_uid")
    private String matricule;

    private String resume;

    private Date createdDate;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passage_patient_uid")
    private PassagePatient passagePatient;*/

    @Column(name = "passage_patient_uid")
    private Long passagePatientId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "document_par_consultation",
            joinColumns = @JoinColumn(name = "consultation_uid"),
            inverseJoinColumns = @JoinColumn(name = "document_uid"))
    private Set<Document> documents;

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
