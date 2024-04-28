package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "observation_clinique")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObservationClinique extends AbstractAuditableEntity implements Serializable {


    @Column(name = "motifs_hospitalisation", columnDefinition = "TEXT")
    private String motifsHospitalisation;

    @Column(name = "histoire_maladie", columnDefinition = "TEXT")
    private String histoireMaladie;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Antecedent antecedent;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private ExamenPhysique examenPhysique;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circuit_patient_uid")
    private CircuitPatient circuitPatient;

    @JoinColumn(name = "circuit_patient_id")
    private Long circuitPatientId;

    private Long createdBy;

    private Date createdDate;

    @CreatedDate
    @Column(nullable = false,
            updatable = false
    )
    @Basic(fetch = FetchType.LAZY)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Basic(fetch = FetchType.LAZY)
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(nullable = false,
            updatable = false
    )
    @Basic(fetch = FetchType.LAZY)
    private @Size(max = 50) String createdByUser;

    @LastModifiedBy
    @Basic(fetch = FetchType.LAZY)
    private @Size(max = 50) String lastModifiedBy;

    private int actif;

    public boolean isActif() {
        if (actif == 1) return true;
        else return false;
    }

    public void setActif(boolean actif) {
        if (actif == true) this.actif = 1;
        else this.actif = 0;
    }

}
