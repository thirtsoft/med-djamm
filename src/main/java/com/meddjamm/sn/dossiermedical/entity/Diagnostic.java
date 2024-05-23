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
@Table(name = "diagnostic")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diagnostic extends AbstractAuditableEntity implements Serializable {

    @Column(columnDefinition = "TEXT")
    private String diagnostic_principal;

    @Column(columnDefinition = "TEXT")
    private String diagnostic_associe;

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
