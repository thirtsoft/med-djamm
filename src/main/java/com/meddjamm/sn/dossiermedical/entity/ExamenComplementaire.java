package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
public class ExamenComplementaire extends AbstractAuditableEntity implements Serializable {

    @Column(columnDefinition = "TEXT")
    private String biologie;

    private String biologieFileName;

    @Column(columnDefinition = "TEXT")
    private String immunologie;

    private String immunologieFileName;

    @Column(columnDefinition = "TEXT")
    private String imagerie;

    private String imagerieFileName;

    @Column(columnDefinition = "TEXT")
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
