package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "examen_complementaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
