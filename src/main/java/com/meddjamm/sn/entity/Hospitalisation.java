package com.meddjamm.sn.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "hospitalisation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hospitalisation extends AbstractAuditableEntity implements Serializable {

    @Column(name = "patient_uid")
    private String indexPatient;

    @Column(name = "medecin_uid")
    private String matricule;

  /*  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passage_patient_uid")
    private PassagePatient passagePatient;*/

    @JoinColumn(name = "passage_patient_uid")
    private Long passagePatientId;

    private String resume;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "document_par_hospitalisation",
            joinColumns = @JoinColumn(name = "hospitalisation_uid"),
            inverseJoinColumns = @JoinColumn(name = "document_uid"))
    private Set<Document> documents;

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
