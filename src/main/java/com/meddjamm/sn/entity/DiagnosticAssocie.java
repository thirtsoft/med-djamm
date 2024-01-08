package com.meddjamm.sn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "diagnostic_associe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticAssocie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String libelle;

    /*
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;*/

    @JoinColumn(name = "patient_id")
    private String indexPatient;

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
