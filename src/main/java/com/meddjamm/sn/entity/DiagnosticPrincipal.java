package com.meddjamm.sn.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "diagnostic_principal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticPrincipal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long scoreObtenu;

    @Column(name = "patient_id")
    private String indexPatient;

    @ManyToOne
    private Maladie maladie;

    @ManyToOne
    private Classification classification;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "critere_utilise_par_diagnostic", joinColumns = @JoinColumn(name = "diagnostic_uid"),
            inverseJoinColumns = @JoinColumn(name = "critere_utilise_uid"))
    private Set<CritereUtilise> critereUtilises;

    private Date createDate;

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
