package com.meddjamm.sn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dossier_patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierPatient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero_dossier", nullable = true, unique = true)
    private int numeroDossier;

    @Column(name = "patient_id")
    private String indexPatient;

    @Column(name = "medecin_id")
    private String indexMedecin;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private DiagnosticPrincipal diagnosticPrincipal;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private DiagnosticAssocie diagnosticAssocie;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private HistoireMaladie histoireMaladie;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private AntecedentMedicaux antecedentMedicaux;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private AntecedentChirurgie antecedentChirurgie;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private AntecedentGynecologie antecedentGynecologie;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private AntecedentFamilial antecedentFamilial;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private ModeVie modeVie;

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
