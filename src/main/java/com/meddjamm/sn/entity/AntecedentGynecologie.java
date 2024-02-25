package com.meddjamm.sn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "antecedent-gynecologie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AntecedentGynecologie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

/*
    private String libelle;

    @Column(name = "patient_id")
    private String indexPatient;*/

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "gynecologie_antecedent_par_antecedent_gynecologie",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "gynecologiques_antecedent")
    private Set<String> gynecologiquesAntecedent;

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
