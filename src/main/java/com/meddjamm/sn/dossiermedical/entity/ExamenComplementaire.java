package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "examen_complementaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamenComplementaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String biologie;

    private String biologieFileName;

    private String immunologie;

    private String immunologieFileName;

    private String imagerie;

    private String imagerieFileName;

    private String anatomopathologie;

    private String anatomopathologieFileName;

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
