package com.meddjamm.sn.entity;


import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "diagnostic_principal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticPrincipal extends AbstractAuditableEntity implements Serializable {

    private Long scoreObtenu;

    @ManyToOne
    private Maladie maladie;


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
