package com.meddjamm.sn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "histoire_maladie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoireMaladie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    @Column(name = "patient_id")
    private String indexPatient;*/

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "antecedent_precedent_hisotire_maladie", joinColumns = @JoinColumn(name = "hisotire_maladie_uid"))
    @Column(name = "antecedent_precedent_uid")
    private Set<Long> antecedentPrecedents;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "antecedent_actuel_hisotire_maladie", joinColumns = @JoinColumn(name = "hisotire_maladie_uid"))
    @Column(name = "antecedent_actuel_uid")
    private Set<Long> antecedentActuels;

    private int age;

    private String circonstance;

    private Date dateDiagnostic;

    private String infoComplementaire;

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